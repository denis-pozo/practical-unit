package org.denispozo.tutorial.testing.c5.exercise.auth;

import static org.mockito.Mockito.*;

import org.denispozo.tutorial.testing.c5.exercise.auth.SecurityService;
import org.denispozo.tutorial.testing.c5.exercise.auth.User;
import org.denispozo.tutorial.testing.c5.exercise.auth.UserDAO;
import org.denispozo.tutorial.testing.c5.exercise.auth.UserService;
import org.junit.Test;

public class UserServiceTest {

    private static final String MD5_PASSWORD = "MD5-Password";
    private SecurityService security = mock(SecurityService.class);
    private UserDAO userDAO = mock(UserDAO.class);
    private User user = mock(User.class);

    private UserService userService = new UserService(userDAO, security);

    @Test
    public void user_shouldGetItsNewPassword() throws Exception {
        when(security.md5(user.getPassword())).thenReturn(MD5_PASSWORD);
        userService.assignPassword(user);

        verify(user).setPassword(MD5_PASSWORD);
    }

    @Test
    public void userDAO_shouldUpdateUserInformation() throws Exception {
        userService.assignPassword(user);

        verify(userDAO).updateUser(user);
    }
}
