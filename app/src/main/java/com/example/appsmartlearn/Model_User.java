package com.example.appsmartlearn;

public class Model_User {
    public String username;
    public String password;
    public String email;
    public String DoB;
    public String id_category;
    public String id_userType;
    public String Engword;
    public String Vieword;

    public Model_User() {}
    public Model_User(String username, String password, String email, String DoB, String id_category, String id_userType)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.DoB = DoB;
        this.id_category = id_category;
        this.id_userType = id_userType;
    }


    public Model_User(String engword, String vieword) {
        this.Engword = engword;
        this.Vieword = vieword;
    }

    public String toString() {
        return ("\t\t\t\t\t" + "English:" + "\t\t\t\t" + this.Engword + "\n" + "\t\t\t\t\t"+ "Meaning:" + "\t\t\t\t" + this.Vieword) ;
    }

}
