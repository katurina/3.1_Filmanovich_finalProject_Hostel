package by.epam.project.hostel.dao.pagination;

import by.epam.project.hostel.entity.pagination.Page;

import java.util.List;

public  interface Pagination<T> {
    Page<T> getAllPageInformation(List<T> list, int currentPage);
}
