package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;
import by.epam.project.hostel.service.exception.EmptyParamServiceException;
import by.epam.project.hostel.service.validation.Validator;

public class GuestroomValidatorImpl implements Validator<Guestroom> {
    public SearchGuestroomsParams validateSearchParams(SearchGuestroomsParams searchParams) throws EmptyParamServiceException {
        if (searchParams == null) {
            throw new EmptyParamServiceException("search parameters == null");
        }
        if (searchParams.getTv() == null) {
            searchParams.setTv(false);
        }
        if (searchParams.getWifi() == null) {
            searchParams.setWifi(false);
        }
        if (searchParams.getShower() == null) {
            searchParams.setShower(false);
        }
        return searchParams;
    }
}
