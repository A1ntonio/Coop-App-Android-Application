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

public class Signup extends AppCompatActivity {

    private TextView signup_back, signup_signin;
    private EditText signup_email, signup_pass, signup_name;
    private AppCompatButton signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //        Variable Initialization
        initialize();

        click_action();
    }

    private void click_action() {
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signup_email.getText().toString();
                String pass = signup_pass.getText().toString();
                String name = signup_name.getText().toString();
                if (!email.equals("")){
                    if (!name.equals("")){

                        if (!pass.equals("")){
                            SignupSpacecraft signupSpacecraft = new SignupSpacecraft(name, email, pass);
                            if (new DBAdapter(Signup.this).signup_user(signupSpacecraft)){
                                Intent intent = new Intent(Signup.this, SignIn.class);
                                startActivity(intent);
                                finish();
                            }else {
                                signup_email.setText("");
                                signup_name.setText("");
                                signup_pass.setText("");
                                Toast.makeText(Signup.this, "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Signup.this, "Password required!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Signup.this, "Name required!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Signup.this, "Email required!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signup_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initialize() {
        signup_back = findViewById(R.id.signup_back);
        signup_signin = findViewById(R.id.signup_signin);
        signup_email = findViewById(R.id.signup_email);
        signup_pass = findViewById(R.id.signup_password);
        signup_btn = findViewById(R.id.signup_btn);
        signup_name = findViewById(R.id.signup_name);
    }
}