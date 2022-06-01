package com.example.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivty extends AppCompatActivity {
    EditText etEmail,etPassword,etName,etPhoneNo;
    Button btnLogin,btnSignUp;

    dbhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activty);

       etEmail=findViewById(R.id.et_EmailSignUp);
       etName=findViewById(R.id.et_NameSignUp);
       etPassword=findViewById(R.id.et_PasswordForSignUp);
       etPhoneNo=findViewById(R.id.et_PhoneNoForSignUp);
       btnLogin=findViewById(R.id.btnSignIn);
       btnSignUp=findViewById(R.id.btnSignUpNow);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Enter email");
                }
                else if (etName.getText().toString().isEmpty()){
                    etName.setError("Enter Name");
                }
                else if (etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Enter password");
                }
                else if (etPhoneNo.getText().toString().isEmpty()){
                    etPhoneNo.setError("Enter phone no");
                }
                else{

                    dbHelper = dbhelper.getInstance(SignupActivty.this);
                    if (dbHelper.CreateAcoount(getDataFromUI())){
                        Toast.makeText(SignupActivty.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivty.this,FoodCategoryActivity.class));
                    }
                }


            }
            public Signup_Model getDataFromUI(){
                Signup_Model signUpModel = new Signup_Model();
                signUpModel.setEmail(etEmail.getText().toString());
                signUpModel.setName(etName.getText().toString());
                signUpModel.setPassword(etPassword.getText().toString());
                signUpModel.setPhoneNo(etPhoneNo.getText().toString());

                return signUpModel;
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivty.this,LoginActivity.class));
            }
        });

    }
}
