package com.puulapp.coop_agent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private TextView signin_back, signin_signup;
    private EditText signin_email, signin_pass;
    private AppCompatButton signin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //        Variable Initialization
        initialize();

        click_action();
    }

    private void click_action() {
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signin_email.getText().toString();
                String pass = signin_pass.getText().toString();
                if (!email.equals("")){
                    if (!pass.equals("")){

                        if (new DBAdapter(SignIn.this).validate(email, pass)){
                            Intent intent = new Intent(SignIn.this, Home.class);
                            startActivity(intent);
                            finish();
                        }else {
                            signin_pass.setText("");
                            signin_email.setText("");
                        }

                    } else {
                        Toast.makeText(SignIn.this, "Password required!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIn.this, "Email required!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        signin_back = findViewById(R.id.signin_back);
        signin_signup = findViewById(R.id.signin_signup);
        signin_email = findViewById(R.id.signin_email);
        signin_pass = findViewById(R.id.signin_password);
        signin_btn = findViewById(R.id.signin_button);
    }
}