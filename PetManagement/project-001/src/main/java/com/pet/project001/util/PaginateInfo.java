package com.pet.project001.util;

/**
 * 分页
 */
public class PaginateInfo {
    private int pageNo;//当前页面号
    private int pageSize;//一个页面存储多少条
    private final int pageCount = 7;//显示页面数量
    private int pageAllCount;//总页面数量
    private long allCount;//总条数
    private int pageBeg;//开始页面号

    private int pageEnd;//结束页面号

    public PaginateInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageAllCount() {
        return pageAllCount;
    }

    public void setPageAllCount(int pageAllCount) {
        this.pageAllCount = pageAllCount;
    }

    public long getAllCount() {
        return allCount;
    }

    public void setAllCount(long allCount) {
        this.allCount = allCount;
    }

    public int getPageBeg() {
        return pageBeg;
    }

    public void setPageBeg(int pageBeg) {
        this.pageBeg = pageBeg;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    //获取起始位置
    public int getOffset(){
        /**
         * 返回每一页的起始位置
         * eg:第一页的起始位置是0（1-1)*20=0;第二页的起始位置是20（2-1）*20=20；
         */
        return (this.pageNo-1)*pageSize;
    }

    //页面显示多少条
    public int getLimit(){
        return this.pageSize;
    }

    //设置各个页面参数
    public void setCount(long cnt){
        //设置记录的总条数
        this.allCount = cnt;

        //设置总页数
        this.pageAllCount = (int)(this.allCount/this.pageSize);
        if(this.allCount%this.pageSize > 0){
            this.pageAllCount++;
        }

        //设置对当前页面违法的处理
        if (this.pageNo < 1) {
            this.pageNo = 1;
        }
        if (this.pageNo > pageAllCount) {
            this.pageNo = pageAllCount;
            if (this.pageNo < 1) {
                this.pageNo = 1;
            }
        }

        int mid = this.pageCount/2;
        //设置开始页面（7个页面的最前面一页）
        this.pageBeg = pageNo - mid;
        if (pageBeg < 1){
            this.pageBeg = 1;
        }
        this.pageEnd = pageBeg + pageCount -1;
        //设置结束页面（7个页面的最后面一页）
        if (pageEnd > pageAllCount){
            this.pageEnd = pageAllCount;
            pageBeg = pageEnd - pageCount +1;
            if (pageBeg < 1) {
                pageBeg = 1;
            }
        }
    }
}
