package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Faq extends AppCompatActivity {

    ImageView faqHome, faqArticle, faqOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        faqHome = findViewById(R.id.faqHome);
        faqArticle = findViewById(R.id.faqArticle);
        faqOrderList = findViewById(R.id.faqOrderList);

        String passedUsername = getIntent().getStringExtra("username");
        String passedPhoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");

        faqArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Faq.this, Article.class);
                intent.putExtra("username", passedUsername);
                intent.putExtra("phoneNo", passedPhoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        faqHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Faq.this, MainActivity.class);
                intent.putExtra("username", passedUsername);
                intent.putExtra("phoneNo", passedPhoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }
}