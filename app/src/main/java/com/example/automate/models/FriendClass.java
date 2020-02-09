package com.example.automate.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendClass {

    @SerializedName("friend_id")
    @Expose
    private Integer friendId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}