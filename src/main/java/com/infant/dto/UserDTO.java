package com.infant.dto;

public class UserDTO {
    private String name;
    private String username;
    private String password;
    private String mobileNumber;
    private String status;
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
            "name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            '}';
    }
}