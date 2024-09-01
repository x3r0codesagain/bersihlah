package com.myapp.bersihlah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myapp.bersihlah.adapter.ArticleRecyclerAdapter;
import com.myapp.bersihlah.model.ArticleModel;
import java.util.ArrayList;

public class Article extends AppCompatActivity {

    DatabaseReference ref;

    ImageView homeButton, orderListButton, faqButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ArrayList<ArticleModel> articleModels = new ArrayList<>();

        homeButton = findViewById(R.id.articleListHome);
        orderListButton= findViewById(R.id.articleListOrderList);
        faqButton = findViewById(R.id.articleListFaq);

        String username = getIntent().getStringExtra("username");
        String phoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");

        RecyclerView articleRV = findViewById(R.id.articleListRV);
        ArticleRecyclerAdapter articleRecyclerAdapter = new ArticleRecyclerAdapter(this, articleModels);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Article.this, MainActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        orderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Article.this, OrderList.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Article.this, Faq.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });



        DatabaseReference rootNode = FirebaseDatabase.getInstance().getReference();
        ref = rootNode.child("articles");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    ArticleModel articleHelper = new ArticleModel(dsp.child("source").getValue(String.class), dsp.child("articleName").getValue(String.class));
                    articleModels.add(articleHelper);

                }
                articleRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        articleRV.setLayoutManager(linearLayoutManager);
        articleRV.setAdapter(articleRecyclerAdapter);


    }


}