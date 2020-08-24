package com.example.java.optional;

/**
 * @Description:
 * @user: yang
 * @Time: 2020/3/21  11:53
 */
public class User {
    private String user;
    private String psw;

    public User(String user, String psw) {
        this.user = user;
        this.psw = psw;
        System.out.println("user" + user);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
