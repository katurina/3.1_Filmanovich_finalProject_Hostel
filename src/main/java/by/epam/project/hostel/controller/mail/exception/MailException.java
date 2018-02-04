package by.epam.project.hostel.controller.mail.exception;


public class MailException extends Exception {

    public MailException() {
        super();
    }

    public MailException(Throwable cause) {
        super(cause);
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
