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
import com.myapp.bersihlah.adapter.ItemRecyclerAdapter;
import com.myapp.bersihlah.model.OrderHelper;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {


    DatabaseReference ref;

    ImageView homeButton, articleButton, faqButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ArrayList<OrderHelper> orderItems = new ArrayList<>();

        homeButton = findViewById(R.id.orderListHome);
        faqButton = findViewById(R.id.orderListFaq);
        articleButton = findViewById(R.id.orderListArticle);

        String username = getIntent().getStringExtra("username");
        String phoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");

        RecyclerView orderRV = findViewById(R.id.orderListRV);
        ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(this, orderItems);

        articleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderList.this, Article.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderList.this, MainActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderList.this, Faq.class);
                intent.putExtra("username", username);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });



        DatabaseReference rootNode = FirebaseDatabase.getInstance().getReference();
        ref = rootNode.child("orders");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Result will be holded Here

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    OrderHelper orderHelper = new OrderHelper(dsp.child("orderID").getValue(String.class), dsp.child("type").getValue(String.class), dsp.child("phoneNo").getValue(String.class),dsp.child("orderID").getValue(String.class), dsp.child("address").getValue(String.class), dsp.child("hour").getValue(String.class), dsp.child("minute").getValue(String.class), dsp.child("price").getValue(String.class), dsp.child("paymentMethod").getValue(String.class), dsp.child("date").getValue(String.class));
                    if(username.equals(dsp.child("username").getValue(String.class))) orderItems.add(orderHelper); //add result into array list
                }
                itemRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        orderRV.setLayoutManager(linearLayoutManager);
        orderRV.setAdapter(itemRecyclerAdapter);


    }
}