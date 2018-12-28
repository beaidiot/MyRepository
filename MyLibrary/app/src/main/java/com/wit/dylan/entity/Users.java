package com.wit.dylan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dylan on 2017/4/13.
 */
public class Users implements Serializable {
    //姓名
    private String userName;

    //学号
    private String userNum;

    //密码
    private String userPwd;
    //旧密码
    private String oldPassword;
    //新密码
    private String newPassword;
    //用户编号
    private String userInfoId;
    //学校编号：初值为 25
    private String campusId="25";
    //学校名称:初值为 武汉工程大学
    private String schoolName="武汉工程大学";
    //学校编号：初值为 e55ae886-18a7-4b5e-9692-e62ad5ff7497
    private String schoolNum="e55ae886-18a7-4b5e-9692-e62ad5ff7497";
    //图书馆编号：初值为 18
    private String buildingID="18";
    //自习室代码
    private String classroomId;

    //位置预定起止时间
    private Date reservationBeginTime;
    private Date reservationEndTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(String schoolNum) {
        this.schoolNum = schoolNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }


    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
