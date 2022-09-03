package com.puulapp.coop_agent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.puulapp.coop_agent.DBAdapter.db;

public class DBOpenHelper extends SQLiteOpenHelper {


    //Constants for db name and version
    private static final String DATABASE_NAME = "coop_agent.db";
    private  static final int DATABASE_VERSION = 2;

    //Constants for identifying table and columns
    public static final String TABLE_SIGNUP = "signup";
    public static final String TABLE_CUSTOMER = "customer";

    public static final String TABLE_PAYMENT = "payment";

    public static final String COL_SIGNUP_ID = "signup_id";
    public static final String COL_SIGNUP_NAME = "signup_name";
    public static final String COL_SIGNUP_EMAIL = "signup_email";
    public static final String COL_SIGNUP_PASS = "signup_pass";


    public static final String COL_CUS_ID = "cus_id";
    public static final String COL_CUS_NAME = "cus_name";
    public static final String COL_CUS_SEX = "cus_sex";
    public static final String COL_CUS_ADDRESS = "cus_address";
    public static final String COL_CUS_PHONE = "cus_phone";
    public static final String COL_CUS_ACCOUNT = "cus_account";
    public static final String COL_CUS_AMOUNT = "cus_amount";
    public static final String COL_CUS_BRANCH = "cus_branch";

    public static final String COL_PAY_ID = "pay_id";
    public static final String COL_PAY_AMOUNT = "pay_amount";
    public static final String COL_PAY_COUNTER_ID = "pay_counter_id";
    public static final String COL_PAY_NAME = "pay_name";

    //SQL to create table
    private static final String TABLE_CREATE_SIGNUP = "CREATE TABLE " + TABLE_SIGNUP + " (" + COL_SIGNUP_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SIGNUP_NAME + " TEXT, " + COL_SIGNUP_EMAIL + " TEXT, " + COL_SIGNUP_PASS + " TEXT);";


    private static final String TABLE_CREATE_PAYMENT = "CREATE TABLE " + TABLE_PAYMENT + " (" + COL_PAY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PAY_AMOUNT + " TEXT, " + COL_PAY_COUNTER_ID + " TEXT, " + COL_PAY_NAME + " TEXT);";


    private static final String TABLE_CREATE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER + " (" + COL_CUS_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CUS_NAME + " TEXT, " + COL_CUS_SEX + " TEXT, " + COL_CUS_ADDRESS + " TEXT, " +
            COL_CUS_PHONE +" TEXT, " + COL_CUS_ACCOUNT + " TEXT, " + COL_CUS_AMOUNT + " TEXT," + COL_CUS_BRANCH + " TEXT);";




    public DBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE_SIGNUP);
        sqLiteDatabase.execSQL(TABLE_CREATE_CUSTOMER);
        sqLiteDatabase.execSQL(TABLE_CREATE_PAYMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNUP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
    }
}
