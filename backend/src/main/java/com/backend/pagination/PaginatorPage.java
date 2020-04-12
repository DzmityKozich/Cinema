package com.backend.pagination;

import java.util.List;
import java.util.Objects;

public class PaginatorPage<E> {

    private List<E> listElements;
    private int pageNumber;
    private int pageSize;
    private boolean isFirst;
    private boolean isLast;
    private int totalPages;
    private long totalElements;

    public PaginatorPage() {
    }

    public PaginatorPage(List<E> listElements, int pageNumber, int pageSize, boolean isFirst, boolean isLast, int totalPages, long totalElements) {
        this.listElements = listElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<E> getListElements() {
        return listElements;
    }

    public void setListElements(List<E> listElements) {
        this.listElements = listElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginatorPage<?> that = (PaginatorPage<?>) o;
        return pageNumber == that.pageNumber &&
                pageSize == that.pageSize &&
                isFirst == that.isFirst &&
                isLast == that.isLast &&
                totalPages == that.totalPages &&
                totalElements == that.totalElements;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize, isFirst, isLast, totalPages, totalElements);
    }
}
