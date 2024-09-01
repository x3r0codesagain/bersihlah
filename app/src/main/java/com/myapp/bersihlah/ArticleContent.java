package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ArticleContent extends AppCompatActivity {

    TextView articleTitle;
    TextView articleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_content);


        articleTitle = findViewById(R.id.articleTitleContent);
        articleContent = findViewById(R.id.articleContent);

        articleTitle.setText(getIntent().getStringExtra("title"));
        articleContent.setText(getIntent().getStringExtra("content"));
    }
}