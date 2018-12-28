package com.wit.dylan.entity;

import java.util.Calendar;

/**
 * Created by Dylan on 2017/4/26.
 */
public class Times {
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar calendar;

    private String seatId;


    public Times() {

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
    }

    public String getTodayMonth_Day() {
        return mMonth + "-" + mDay;
    }

    public String getTomorrowMonth_Day() {


        return ((mDay+1)>31?mMonth+1:mMonth) + "-" + (mDay + 1)%31;
    }

    public String getToday() {
        return mYear + "-" + mMonth + "-" + mDay;
    }

    public String getTomorrow() {
        return mYear + "-" + ((mDay+1)>31?mMonth+1:mMonth) + "-" + ((mDay + 1)%31);
    }

    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMinute() {
        return mMinute;
    }


    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
}
