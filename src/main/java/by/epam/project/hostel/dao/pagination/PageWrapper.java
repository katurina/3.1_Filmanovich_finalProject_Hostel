package by.epam.project.hostel.dao.pagination;


import by.epam.project.hostel.entity.pagination.Page;

import java.util.List;

public class PageWrapper {

    public static <T> Page<T> wrapList(List<T> list, int currentPage, int numberOfPages) {
        Page<T> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setNumberOfPages(numberOfPages);
        page.setEntity(list);
        return page;
    }
}
