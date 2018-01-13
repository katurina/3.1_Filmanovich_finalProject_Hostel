package by.epam.project.hostel.service.validation.impl;

import by.epam.project.hostel.entity.Guestroom;
import by.epam.project.hostel.entity.search.SearchGuestroomsParams;
import by.epam.project.hostel.service.constant.Constants;
import by.epam.project.hostel.service.exception.EmptyParamServiceException;
import by.epam.project.hostel.service.exception.SearchParamsServiceException;
import by.epam.project.hostel.service.exception.ServiceException;
import by.epam.project.hostel.service.validation.Validator;

public class GuestroomValidatorImpl implements Validator<Guestroom> {
    public SearchGuestroomsParams validateSearchParams(SearchGuestroomsParams searchParams) throws ServiceException {
        if (searchParams == null) {
            throw new EmptyParamServiceException("search parameters == null");
        }
        if (searchParams.getTv() == null) {
            searchParams.setTv(false);
        }
        if (searchParams.getWifi() == null) {
            searchParams.setWifi(false);
        }
        if (searchParams.getDateFrom() != null && searchParams.getDateTo() != null && searchParams.getDateFrom().isAfter(searchParams.getDateTo())) {
            throw new SearchParamsServiceException("date from" + searchParams.getDateFrom() + "is after date to " + searchParams.getDateTo(), Constants.ErrorParamMessages.INCORRECT_DATE);
        }
        if (searchParams.getNightPriceTo() != null && searchParams.getNightPriceFrom() != null) {
            if (searchParams.getNightPriceTo().compareTo(searchParams.getNightPriceFrom()) < 0
                    || searchParams.getNightPriceFrom().intValue() < 1
                    || searchParams.getNightPriceTo().intValue() < 1) {
                throw new SearchParamsServiceException("incorrect input of night prices", Constants.ErrorParamMessages.INCORRECT_NIGHT_PRICE);
            }
        }
        if (searchParams.getShower() == null) {
            searchParams.setShower(false);
        }
        if (searchParams.getCapacityFrom() != null && searchParams.getCapacityTo() != null) {
            if (searchParams.getCapacityFrom() > searchParams.getCapacityTo() || searchParams.getCapacityTo() < 1 || searchParams.getCapacityFrom() < 1) {
                throw new SearchParamsServiceException("incorrect input of room capacity", Constants.ErrorParamMessages.INCORRECT_CAPACITY);
            }
        }
        return searchParams;
    }
}

