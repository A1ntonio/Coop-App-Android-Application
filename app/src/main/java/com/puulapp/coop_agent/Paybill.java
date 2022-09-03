package com.puulapp.coop_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Paybill extends AppCompatActivity {

    private TextView paybill_back;
    private RadioGroup paybill_radio;
    private AppCompatButton paybill_button;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybill);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        paybill_back = findViewById(R.id.paybill_back);
        paybill_radio = findViewById(R.id.paybill_radio);
        paybill_button = findViewById(R.id.paybill_button);

        paybill_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        paybill_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                j = i;
            }
        });

        paybill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j == 0) {
                    Intent intent = new Intent(Paybill.this, Payment.class);
                    startActivity(intent);
                }
            }
        });
    }
}