package com.ssm.tmall.util;

public class Page {
    private static int defaultcount = 5;
    String param;
    private int start;
    private int count;
    private int total;

    public Page() {
        count = defaultcount;
    }

    public Page(int start, int count) {
        this();
        this.count = count;
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHasPreviouse() {
        return start != 0;
    }

    public boolean isHasNext() {
        return start != getLast();
    }

    public int getTotalPage() {
        int totalPage = total / count;
        if (total % count != 0)
            totalPage++;
        if (0 == totalPage)
            totalPage = 1;
        return totalPage;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }

    public int getLast() {
        int last;
        if (0 != total % count)
            last = total - total % count;
        else
            last = total - count;
        last = last < 0 ? 0 : last;
        return last;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
