package com.example.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et_Email_For_Login, et_Password_For_Login;
    Button btnLogin, btnSignup;
    dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_Email_For_Login=findViewById(R.id.Et_Email_for_Login);
        et_Password_For_Login=findViewById(R.id.Et_Password_For_Login);
        btnLogin=findViewById(R.id.Btn_Login);
        btnSignup=findViewById(R.id.Btn_SignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et_Email_For_Login.getText().toString();
                String password=et_Password_For_Login.getText().toString();
                Log.d("ddd","Password ="+password);
               dbhelper =dbhelper.getInstance(LoginActivity.this);
               String searchpassword=dbhelper.SearchPassword(email);
               Log.d("ddd","Search ="+searchpassword);
               if(password.equals(searchpassword)){
                   Toast.makeText(LoginActivity.this, "LoginSuccesfully", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(LoginActivity.this,FoodCategoryActivity.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(LoginActivity.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
               }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivty.class));

            }
        });
    }
}
