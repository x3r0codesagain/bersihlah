package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {

    TextView usernameText, phoneNoText, emailText;
    Button logoutButton, homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameText = findViewById(R.id.userProfileName);
        phoneNoText = findViewById(R.id.userProfilePhoneNo);
        emailText = findViewById(R.id.userProfileEmail);
        homeButton = findViewById(R.id.homeProfile);
        logoutButton = findViewById(R.id.logoutButton);

        usernameText.setText(getIntent().getStringExtra("username"));
        phoneNoText.setText(getIntent().getStringExtra("phoneNo"));
        emailText.setText(getIntent().getStringExtra("email"));

        String passedUsername = getIntent().getStringExtra("username");
        String passedPhoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profileActivity.this, MainActivity.class);
                intent.putExtra("username", passedUsername);
                intent.putExtra("phoneNo", passedPhoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profileActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }
}