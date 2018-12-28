package com.wit.dylan.entity;

import java.io.Serializable;

/**
 * Created by Dylan on 2017/5/2.
 */
public class OrderDetails implements Serializable {
    private String startDate;
    private String endDate;
    private String seatId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
}
