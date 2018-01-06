package by.epam.project.hostel.entity.pagination;

import java.util.List;

public class Page<T> {
    private int currentPage;
    private int numberOfPages;
    private List<T> entity;

    public Page() {
    }

    public Page(int currentPage, int numberOfPages, List<T> entity) {
        this.currentPage = currentPage;
        this.numberOfPages = numberOfPages;
        this.entity = entity;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<T> getEntity() {
        return entity;
    }

    public void setEntity(List<T> entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", numberOfPages=" + numberOfPages +
                ", entity=" + entity +
                '}';
    }
}
