package com.puulapp.coop_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Create extends AppCompatActivity {

    private EditText fname, lname, mname, address, amount, phone, branch, account;
    private RadioGroup sex;
    private RadioButton male, female;
    private AppCompatButton create;
    private TextView create_back;
    String sexx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        create_back = findViewById(R.id.create_back);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        mname = findViewById(R.id.mname);
        address = findViewById(R.id.address);
        amount = findViewById(R.id.amount);
        phone = findViewById(R.id.phonenumber);
        branch = findViewById(R.id.branch);
        account = findViewById(R.id.account);
        sex = findViewById(R.id.sex);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        create = findViewById(R.id.createcustomer);

        create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sex.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == 0){
                sexx = "male";
            }else {
                sexx = "female";
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnamee = fname.getText().toString() + " " + lname.getText().toString() + " " + mname.getText().toString();
                String addresss = address.getText().toString();
                String amountt = amount.getText().toString();
                String phonee = phone.getText().toString();
                String branchh = branch.getText().toString();
                String accountt = account.getText().toString();

                if (!fname.getText().toString().isEmpty()){
                if (!addresss.isEmpty()) {
                    if (!amountt.isEmpty()) {
                        if (!phonee.isEmpty()) {
                            if (!branchh.isEmpty()) {
                                if (!accountt.isEmpty()) {
                                    CustomerSpacecraft customerSpacecraft = new CustomerSpacecraft(fnamee, addresss, phonee, accountt, amountt, branchh, sexx);
                                    if (new DBAdapter(Create.this).create_customer(customerSpacecraft)) {
                                        showMyDialog(Create.this);
                                    }
                                }
                            }
                            }
                        }
                    }
                }
            }
        });
    }

    private void showMyDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.checkpopup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView textView = (TextView) dialog.findViewById(R.id.textamount);
        TextView textok = (TextView) dialog.findViewById(R.id.textok);

        textView.setText("Registered!");

        textok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /**
         * if you want the dialog to be specific size, do the following
         * this will cover 85% of the screen (85% width and 85% height)
         */
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();
    }
}