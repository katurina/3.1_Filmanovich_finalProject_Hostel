package by.epam.project.hostel.service.exception;

public class UserEmptyParamServiceException extends ServiceException {
    public UserEmptyParamServiceException() {
    }

    public UserEmptyParamServiceException(String message) {
        super(message);
    }

    public UserEmptyParamServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmptyParamServiceException(Throwable cause) {
        super(cause);
    }
}
