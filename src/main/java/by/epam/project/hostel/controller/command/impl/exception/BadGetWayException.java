package by.epam.project.hostel.controller.command.impl.exception;

public class BadGetWayException extends RuntimeException {
    public BadGetWayException() {
    }

    public BadGetWayException(String message) {
        super(message);
    }

    public BadGetWayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGetWayException(Throwable cause) {
        super(cause);
    }
}
