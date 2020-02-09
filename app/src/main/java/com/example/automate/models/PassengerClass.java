
package com.example.automate.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengerClass {

    @SerializedName("passengerId")
    @Expose
    private Integer passengerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("friends")
    @Expose
    private List<Friend> friends = null;
    @SerializedName("histories")
    @Expose
    private List<History> histories = null;

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public class Friend {

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

    public class History {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("passengerId")
        @Expose
        private Integer passengerId;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("destination")
        @Expose
        private String destination;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPassengerId() {
            return passengerId;
        }

        public void setPassengerId(Integer passengerId) {
            this.passengerId = passengerId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

    }

}