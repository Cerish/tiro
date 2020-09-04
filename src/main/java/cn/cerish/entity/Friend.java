package cn.cerish.entity;

import org.springframework.security.core.userdetails.UserDetails;

public class Friend {
    private int id;
    private int userId;
    private int userRoleId;
    private String userName;
    private int friendId;
    private int friendRoleId;
    private String friendName;
    private String friendUserface;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getFriendRoleId() {
        return friendRoleId;
    }

    public void setFriendRoleId(int friendRoleId) {
        this.friendRoleId = friendRoleId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendUserface() {
        return friendUserface;
    }

    public void setFriendUserface(String friendUserface) {
        this.friendUserface = friendUserface;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", userRoleId=" + userRoleId +
                ", userName='" + userName + '\'' +
                ", friendId=" + friendId +
                ", friendRoleId=" + friendRoleId +
                ", friendName='" + friendName + '\'' +
                ", friendUserface='" + friendUserface + '\'' +
                '}';
    }
}
