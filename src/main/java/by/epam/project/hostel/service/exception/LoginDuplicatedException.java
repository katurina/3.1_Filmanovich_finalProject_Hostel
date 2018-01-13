package by.epam.project.hostel.service.exception;

import by.epam.project.hostel.dao.exception.SuchLoginExistException;

public class LoginDuplicatedException extends ServiceException {
    private String errorParam;

    public LoginDuplicatedException() {
    }

    public LoginDuplicatedException(String message) {
        super(message);
    }

    public LoginDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginDuplicatedException(Throwable cause) {
        super(cause);
    }

    public LoginDuplicatedException(String message, SuchLoginExistException e, String errorParam) {
        super(message, e);
        this.errorParam = errorParam;
    }

    public String getErrorParam() {
        return errorParam;
    }
}
