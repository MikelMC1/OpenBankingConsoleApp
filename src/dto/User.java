package dto;

import enums.UserRole;

public class User {

    private long    userId;
    private String username;
    private String password;

    //add in constructor and getter setter
    private UserRole role;


    public User(long userId,String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public User(long userId, String username, String password) {
        this(userId, username, password, null);
    }
    public UserRole getRole() {
        return role;
    }

    public long getUserId() {
        return userId;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
