package org.denispozo.tutorial.testing.c5.exercise;

public class UserService {

    private UserDAO userDAO;
    private SecurityService securityService;

    public UserService(UserDAO userDao, SecurityService security) {
        this.userDAO = userDao;
        this.securityService = security;
    }

    public void assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.md5(user.getPassword());
        user.setPassword(passwordMd5);
        userDAO.updateUser(user);
    }
}
