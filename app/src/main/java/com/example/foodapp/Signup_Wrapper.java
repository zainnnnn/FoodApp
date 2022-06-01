package com.example.foodapp;

public class Signup_Wrapper {
    public static final String TABLE_NAME        = "SignUp";

    public static final String COLUMN_EMAIL      = "Email";
    public static final String COLUMN_NAME       = "Name";
    public static final String COLUMN_PASSWORD   = "Password";
    public static final String COLUMN_PHONE_NO   = "PhoneNo";

    public static final String CreateSignUpTable = String.format("CREATE TABLE IF NOT EXISTS "+"%s(%s TEXT PRIMARY KEY,%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME,COLUMN_EMAIL,COLUMN_NAME,COLUMN_PASSWORD,COLUMN_PHONE_NO);

    public static final String DropSignUpTable = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SelectSignUpAllRecords = "SELECT * FROM "+TABLE_NAME;
//    public static final String ToCheckAnswer        =  "SELECT * FROM "+TABLE_NAME+" Where "+COLUMN_EMAIL+" = ? AND "+Col_Question+" = ? AND "+Col_Answer+" = ?";

}
