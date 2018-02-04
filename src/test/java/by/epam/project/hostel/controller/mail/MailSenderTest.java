package by.epam.project.hostel.controller.mail;

import by.epam.project.hostel.controller.mail.exception.MailException;
import org.junit.Test;

public class MailSenderTest {

    @Test
    public void sendMessage() {

        try {
            MailSender.sendMessage("lala", "hi", "kate.filmanovich@gmail.com");
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}