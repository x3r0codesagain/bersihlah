package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myapp.bersihlah.model.UserHelper;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputLayout registerUsername, registerEmail, registerPassword, registerPhoneNo;
    Button registerButton;

    FirebaseDatabase rootNode;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerUsername = findViewById(R.id.usernameRegister);
        registerEmail = findViewById(R.id.emailRegister);
        registerPassword = findViewById(R.id.passwordRegister);
        registerPhoneNo = findViewById(R.id.phoneRegister);
        registerButton = findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                ref = rootNode.getReference("users");


                registerUser(view);
                if(validateUsername() && validateEmail() && validatePassword() && validatePhoneNo()){
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }


            }

        });


    }

    private Boolean validateUsername(){
        String value = registerUsername.getEditText().getText().toString();

        if(value.isEmpty()){
            registerUsername.setError("Field is empty");
            return false;
        }
        else if(value.length() > 20){
            registerUsername.setError("Username is too long");
            return false;
        }
        else{
            registerUsername.setError(null);
            registerUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail(){
        String value = registerEmail.getEditText().getText().toString();
        String emailChecker = "[a-zA0-9._-]+@[a-z]+\\.+[a-z]+$";

        if(value.isEmpty()){
            registerEmail.setError("Field is empty");
            return false;
        }
        else if(value.matches(emailChecker) == false){
            registerEmail.setError("invalid email");
            return false;
        }
        else{
            registerEmail.setError(null);
            registerEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePhoneNo(){
        String value = registerPhoneNo.getEditText().getText().toString();

        if(value.isEmpty()) {
            registerPhoneNo.setError("Field is empty");
            return false;
        }
        else{
            registerPhoneNo.setError(null);
            registerPhoneNo.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword(){
        String value = registerPassword.getEditText().getText().toString();

        if(value.isEmpty()) {
            registerPassword.setError("Field is empty");
            return false;
        }
        else{
            registerPassword.setError(null);
            registerPassword.setErrorEnabled(false);
            return true;
        }

    }


    public void registerUser(View v){
        if(!validateUsername() | !validateEmail() | !validatePassword() | !validatePhoneNo()){
            return;
        }

        String username = registerUsername.getEditText().getText().toString();
        String email = registerEmail.getEditText().getText().toString();
        String phoneNo = registerPhoneNo.getEditText().getText().toString();
        String password = registerPassword.getEditText().getText().toString();

        UserHelper userData = new UserHelper(username,email, phoneNo, password);

        ref.child(username).setValue(userData);
    }

}