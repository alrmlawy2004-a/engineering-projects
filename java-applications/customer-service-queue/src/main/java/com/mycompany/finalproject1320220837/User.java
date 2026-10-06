
package com.mycompany.finalproject1320220837;


class User {
    String username;
    int password;
    boolean isAdmin;
    boolean isActive;

    public User(String username, int password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = true;
    }
}

