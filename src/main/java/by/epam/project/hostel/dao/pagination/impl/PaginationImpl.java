package by.epam.project.hostel.dao.pagination.impl;


import by.epam.project.hostel.dao.pagination.Pagination;
import by.epam.project.hostel.entity.pagination.Page;

import java.util.List;

public class PaginationImpl<T> implements Pagination<T> {

    private static final int MAX_ENTRIES_PER_PAGE = 5;
//todo
    @Override
    public Page<T> getAllPageInformation(List<T> list, int currentPage) {
        Page<T> page = new Page<>();
        page.setCurrentPage(currentPage);
        int numberOfPages = getPage(list, MAX_ENTRIES_PER_PAGE);
        page.setNumberOfPages(numberOfPages);
        int offset = MAX_ENTRIES_PER_PAGE * (currentPage - 1);
        List<T> listByOffsetAndLength = getListByOffsetAndLength(offset, list);
        page.setEntity(listByOffsetAndLength);
        return page;
    }


    private List<T> getListByOffsetAndLength(int offset, List<T> list) {
        int to = offset + MAX_ENTRIES_PER_PAGE;
        if (offset > list.size()) {
            offset = list.size();
        }
        if (to > list.size()) {
            to = list.size();
        }
        return list.subList(offset, to);
    }


    private int getPage(List<T> list, int length) {
        int pages = list.size() / length;
        if (list.size() % length != 0) {
            pages += 1;
        }
        return pages;
    }

}
