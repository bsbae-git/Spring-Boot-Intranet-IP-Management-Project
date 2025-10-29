package com.jules.ipam.dto;

public class SearchCriteriaDTO {
    private String ipAddress;
    private String status;
    private String userId;
    private int page = 1;
    private int pageSize = 10;

    // Manual Getters
    public String getIpAddress() {
        return ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    // Manual Setters
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
