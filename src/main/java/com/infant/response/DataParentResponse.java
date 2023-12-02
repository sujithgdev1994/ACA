package com.infant.response;

import java.util.List;

public class DataParentResponse {
    private List<DataResponse> dataResponses;
    private long totalCount;
    private int pageSize;

    public List<DataResponse> getDataResponses() {
        return dataResponses;
    }

    public void setDataResponses(List<DataResponse> dataResponses) {
        this.dataResponses = dataResponses;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public DataParentResponse(List<DataResponse> dataResponses, long totalCount, int pageSize) {
        super();
        this.dataResponses = dataResponses;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }
}