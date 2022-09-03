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

public class Payment extends AppCompatActivity {

    private TextView payment_back;
    private EditText payment_counter_id, payment_customer_name, payment_amount;
    private AppCompatButton payment_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        payment_back = findViewById(R.id.payment_back);
        payment_counter_id = findViewById(R.id.payment_counter_id);
        payment_customer_name = findViewById(R.id.payment_customer_name);
        payment_amount = findViewById(R.id.payment_amount);
        payment_btn = findViewById(R.id.payment_btn);

        payment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = payment_amount.getText().toString();
                String id = payment_counter_id.getText().toString();
                String name = payment_customer_name.getText().toString();

                if (!id.isEmpty()) {
                    if (!name.isEmpty()) {
                        if (!amount.isEmpty()) {
                            if (new DBAdapter(Payment.this).payment(amount, id, name)) {
                                showMyDialog(Payment.this);
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

        textView.setText("Payment successful!");

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