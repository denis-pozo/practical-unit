package org.denispozo.tutorial.testing.c5.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class MessengerTest {

    private MailServer mailServer = mock(MailServer.class);
    private TemplateEngine templateEngine = mock(TemplateEngine.class);
    private Template template = mock(Template.class);
    private Client client = mock(Client.class);
    private final String MSG_CONTENT = "Message Content. End.";
    private final String EMAIL_ADDRESS = "some@email.com";

    private Messenger messenger = new Messenger(mailServer, templateEngine);

    @Test
    public void test() {
        when(templateEngine.prepareMessage(template, client)).thenReturn(MSG_CONTENT);
        when(client.getEmail()).thenReturn(EMAIL_ADDRESS);

        messenger.sendMessage(client, template);
        verify(mailServer).send(EMAIL_ADDRESS, MSG_CONTENT);
    }




}
