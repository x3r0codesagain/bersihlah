package com.myapp.bersihlah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView registerHere, textLogin;
    ImageView loginLogo;
    TextInputLayout usernameLogin;
    TextInputLayout passwordLogin;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerHere = findViewById(R.id.registerLogin);
        loginLogo = findViewById(R.id.loginLogo);
        textLogin = findViewById(R.id.textLogin);
        usernameLogin = findViewById(R.id.usernameLogin);
        passwordLogin = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        registerHere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                loginUser(v);

            }
        });


    }

    private Boolean validateUsername(){
        String username = usernameLogin.getEditText().getText().toString();


        if(username.isEmpty()){
            usernameLogin.setError("Please fill in your username");
            return false;
        }

        else{
            usernameLogin.setError(null);
            usernameLogin.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword(){
        String username = passwordLogin.getEditText().getText().toString();


        if(username.isEmpty()){
            passwordLogin.setError("Please fill in your password");
            return false;
        }

        else{
            passwordLogin.setError(null);
            passwordLogin.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(View view){

        if(!validateUsername() | !validatePassword()){
            return;
        }

        else{
            isUser();
        }

    }

    private void isUser() {

        final String usernameEntered = usernameLogin.getEditText().getText().toString().trim();
        final String passwordEntered = passwordLogin.getEditText().getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

        Query check = databaseReference.orderByChild("username").equalTo(usernameEntered);

        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    usernameLogin.setError(null);
                    usernameLogin.setErrorEnabled(false);

                    String databasePassword = snapshot.child(usernameEntered).child("password").getValue(String.class);
                    if(databasePassword.equals(passwordEntered)){
                        usernameLogin.setError(null);
                        usernameLogin.setErrorEnabled(false);
                        String databaseUsername = snapshot.child(usernameEntered).child("username").getValue(String.class);
                        String databaseEmail = snapshot.child(usernameEntered).child("email").getValue(String.class);
                        String databasePhoneNo = snapshot.child(usernameEntered).child("phoneNo").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("username", databaseUsername);
                        intent.putExtra("email", databaseEmail);
                        intent.putExtra("password", databasePassword);
                        intent.putExtra("phoneNo", databasePhoneNo);

                        startActivity(intent);

                    }
                    else{
                        passwordLogin.setError("Password Incorrect");
                        passwordLogin.requestFocus();
                    }

                }

                else {
                    usernameLogin.setError("No user found");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}