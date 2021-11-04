package com.example.candystore;

import java.text.DecimalFormat;

public class User {
    private String email;
    private String pass;
    private String fname;
    private String lname;

    public User( String newEmail, String newPass, String newFname, String newLname ) {
        setEmail( newEmail );
        setPass( newPass );
        setFname( newFname );
        setLname( newLname );
    }

    public void setEmail( String newEmail ) {
        email = newEmail;
    }

    public void setPass( String newPass ) {
        pass = newPass;
    }

    public void setFname( String newFname ) {
        fname = newFname;
    }

    public void setLname( String newLname ) {
        lname = newLname;
    }

    public String getEmail( ) {
        return email;
    }

    public String getPass( ) {
        return pass;
    }

    public String getFname( ) {
        return fname;
    }

    public String getLname( ) {
        return lname;
    }

    public String toString( ) {
        return (  "User: " +  fname + " " + lname );
    }
}