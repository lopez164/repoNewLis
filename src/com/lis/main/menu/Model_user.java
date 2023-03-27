
package com.lis.main.menu;

import javax.swing.Icon;


public class Model_user {

    int userId;
    String userName;
    String password;
    Icon profile;
    
    public Model_user() {
    }

    public Model_user(int userId, String userName, Icon profile) {
        this.userId = userId;
        this.userName = userName;
        this.profile = profile;
    }
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Icon getProfile() {
        return profile;
    }

    public void setProfile(Icon profile) {
        this.profile = profile;
    }
    
    
    
}
