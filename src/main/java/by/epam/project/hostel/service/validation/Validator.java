package by.epam.project.hostel.service.validation;

import by.epam.project.hostel.service.exception.UserEmptyParamServiceException;

public interface Validator<T> {
    void validate(T entity) throws UserEmptyParamServiceException;

    default void validate(String... param) throws UserEmptyParamServiceException {
        if (param != null) {
            for (String parm : param) {
                if (parm == null || parm.isEmpty()) {
                    throw new UserEmptyParamServiceException("empty param" + parm);
                }
            }
        } else {
            throw new UserEmptyParamServiceException("not contains params");
        }
    }
}
