package com.yangjianzhou.baobaotao.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjianzhou on 15-6-13.
 */
public class Pagination implements Serializable {

    private static int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private long start;
    private List data;
    private long totalCount;

    public Pagination() {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    public Pagination(long start, long totalCount, int pageSize, List data) {
        this.start = start;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.data = data;
    }

    public long getTotalPage() {
        long totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage = totalPage + 1;
        }
        return totalPage;
    }

    public long getCurrentPageNo() {
        return start / pageSize + 1;
    }

    public boolean hasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPage();
    }

    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
}
