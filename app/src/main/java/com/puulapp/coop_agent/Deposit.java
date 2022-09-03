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
import android.widget.TextView;
import android.widget.Toast;

public class Deposit extends AppCompatActivity {

    private EditText deposit_cus_name, deposit_account_num, deposit_amount;
    private AppCompatButton deposit_button, deposit_search;
    private TextView deposit_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        deposit_back = findViewById(R.id.deposit_back);
        deposit_account_num = findViewById(R.id.deposit_account);
        deposit_cus_name = findViewById(R.id.deposit_cus_name);
        deposit_amount = findViewById(R.id.deposit_amount);
        deposit_button = findViewById(R.id.deposit_button);
        deposit_search = findViewById(R.id.deposit_search);

        deposit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deposit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = deposit_account_num.getText().toString();

                if (!account.isEmpty()) {

                    if (new DBAdapter(Deposit.this).retrieveAllColumn(account) == null) {
                        Toast.makeText(Deposit.this, "Account not exist!", Toast.LENGTH_SHORT).show();
                    } else {
                        CustomerSpacecraft cf = new DBAdapter(Deposit.this).retrieveAllColumn(account);
                        showMyDialog2(Deposit.this, cf);
                    }
                }
            }
        });

        deposit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = deposit_account_num.getText().toString();
                String amount = deposit_amount.getText().toString();
                if (!account.isEmpty()) {
                    if (!amount.isEmpty()) {
                        if (new DBAdapter(Deposit.this).updateAmount(account, amount)) {
                            showMyDialog(Deposit.this);
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

        textView.setText("Deposited!");

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
    private void showMyDialog2(Context context, CustomerSpacecraft cf) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.checkpopup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView textView = (TextView) dialog.findViewById(R.id.textamount);
        TextView textok = (TextView) dialog.findViewById(R.id.textok);

        textView.setText(cf.getFname() + "\n" + cf.getPhone() + "\n" + cf.getAddress() + "\n" + cf.getAmount());

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