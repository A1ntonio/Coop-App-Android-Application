package com.puulapp.coop_agent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBAdapter {

    Context c;
    static SQLiteDatabase db;
    static DBOpenHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper = new DBOpenHelper(c);
    }

    public boolean signup_user(SignupSpacecraft spacecraft) {
        boolean check = false;
        try {
            db = helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.COL_SIGNUP_NAME, spacecraft.getName());
            cv.put(DBOpenHelper.COL_SIGNUP_EMAIL, spacecraft.getEmail());
            cv.put(DBOpenHelper.COL_SIGNUP_PASS, spacecraft.getPassword());

            long result = db.insert(DBOpenHelper.TABLE_SIGNUP, null, cv);
            if (result > 0) {
                check = true;
                Toast.makeText(c, "Registered!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(c, "Not Registered!", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }
        return check;
    }
    public boolean create_customer(CustomerSpacecraft spacecraft) {
        boolean check = false;
        try {
            db = helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.COL_CUS_NAME, spacecraft.getFname());
            cv.put(DBOpenHelper.COL_CUS_SEX, spacecraft.getSex());
            cv.put(DBOpenHelper.COL_CUS_ADDRESS, spacecraft.getAddress());
            cv.put(DBOpenHelper.COL_CUS_PHONE, spacecraft.getPhone());
            cv.put(DBOpenHelper.COL_CUS_ACCOUNT, spacecraft.getAccount());
            cv.put(DBOpenHelper.COL_CUS_AMOUNT, spacecraft.getAmount());
            cv.put(DBOpenHelper.COL_CUS_BRANCH, spacecraft.getBranch());

            long result = db.insert(DBOpenHelper.TABLE_CUSTOMER, null, cv);
            if (result > 0) {
                check = true;
                Toast.makeText(c, "Registered!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(c, "Not Registered!", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }
        return check;
    }

    public Boolean validate(String email, String password) {
        boolean check = false;
        try {
            db = helper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM " + DBOpenHelper.TABLE_SIGNUP + " WHERE " + DBOpenHelper.COL_SIGNUP_EMAIL + " = '" + email + "';", null);

            while (c.moveToNext()) {
                if (password.equals(c.getString(3))){
                    check = true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }

        return check;
    }

    public CustomerSpacecraft retrieveAllColumn(String account) {
        CustomerSpacecraft s = null;
        try {
            db = helper.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM " + DBOpenHelper.TABLE_CUSTOMER + " WHERE " + DBOpenHelper.COL_CUS_ACCOUNT + " = '" + account + "';", null);
            String cus_name, cus_sex, cus_address, cus_phone, cus_account, cus_amount, cus_branch;
            while (c.moveToNext()) {
                cus_name = c.getString(1);
                cus_sex = c.getString(2);
                cus_address = c.getString(3);
                cus_phone = c.getString(4);
                cus_account = c.getString(5);
                cus_amount = c.getString(6);
                cus_branch = c.getString(7);
                s = new CustomerSpacecraft(cus_name, cus_sex, cus_address, cus_phone, cus_account, cus_amount, cus_branch);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }

        return s;
    }

    public boolean updateAmount(String account, String amount) {
        boolean check = false;
        try {
            db = helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.COL_CUS_AMOUNT, amount);

            String selection = DBOpenHelper.COL_CUS_ACCOUNT + " = '" + account + "'";

            long result = db.update(DBOpenHelper.TABLE_CUSTOMER, cv, selection, null);
            if (result > 0) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }
        return check;
    }

    public boolean payment(String amount, String id, String name) {
        boolean check = false;
        try {
            db = helper.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.COL_PAY_AMOUNT, amount);
            cv.put(DBOpenHelper.COL_PAY_COUNTER_ID, id);
            cv.put(DBOpenHelper.COL_PAY_NAME, name);

            long result = db.insert(DBOpenHelper.TABLE_PAYMENT, null, cv);
            if (result > 0) {
                check = true;
                Toast.makeText(c, "Payment Successful!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(c, "Not Successful!", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helper.close();
        }
        return check;
    }
}
