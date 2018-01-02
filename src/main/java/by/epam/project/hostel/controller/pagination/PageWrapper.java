package by.epam.project.hostel.controller.pagination;


import by.epam.project.hostel.entity.pagination.Page;

import java.util.List;

public class PageWrapper {
    public static final int MAX_ENTRIES_PER_PAGE = 5;


    public static <T> Page<T> wrapList(List<T> list, int currentPage, int numberOfPages) {
        Page<T> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setNumberOfPages(numberOfPages);
        page.setEntity(list);
        return page;
    }
}
