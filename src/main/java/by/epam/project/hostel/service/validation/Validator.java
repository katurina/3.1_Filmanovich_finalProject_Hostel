package by.epam.project.hostel.service.validation;

import by.epam.project.hostel.service.exception.EmptyParamServiceException;
import by.epam.project.hostel.service.exception.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws EmptyParamServiceException;

    default void validate(String... param) throws EmptyParamServiceException {
        if (param != null) {
            for (String parm : param) {
                if (parm == null || parm.isEmpty()) {
                    throw new EmptyParamServiceException("empty param" + parm);
                }
            }
        } else {
            throw new EmptyParamServiceException("not contains params");
        }
    }

    default void validateID(int id) throws ValidationException {
        if (id < 1) {
            throw new ValidationException("error during validation id: " + id);
        }
    }

}
