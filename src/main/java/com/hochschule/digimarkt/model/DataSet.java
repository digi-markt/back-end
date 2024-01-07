package com.hochschule.digimarkt.model;

public class DataSet {

    private int totalUser;

    private int totalAdd;

    private int approvedAdd;

    private int pendingAdd;

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public int getTotalAdd() {
        return totalAdd;
    }

    public void setTotalAdd(int totalAdd) {
        this.totalAdd = totalAdd;
    }

    public int getApprovedAdd() {
        return approvedAdd;
    }

    public void setApprovedAdd(int approvedAdd) {
        this.approvedAdd = approvedAdd;
    }

    public int getPendingAdd() {
        return pendingAdd;
    }

    public void setPendingAdd(int pendingAdd) {
        this.pendingAdd = pendingAdd;
    }
}
