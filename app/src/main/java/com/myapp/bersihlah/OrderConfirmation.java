package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderConfirmation extends AppCompatActivity {

    TextView address, duration, date, paymentMethod, orderType;
    Button cancelButton, homeButton;
    private String orderID;

    FirebaseDatabase rootNode;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);


        address = findViewById(R.id.orderAddress);
        duration = findViewById(R.id.orderDuration);
        date = findViewById(R.id.orderDate);
        paymentMethod = findViewById(R.id.orderPayment);
        orderType = findViewById(R.id.orderCat);

        homeButton = findViewById(R.id.backHomeButton);
        cancelButton = findViewById(R.id.cancelButton);



        address.setText(getIntent().getStringExtra("address"));
        date.setText(getIntent().getStringExtra("date"));
        paymentMethod.setText(getIntent().getStringExtra("paymentMethod"));
        orderType.setText(getIntent().getStringExtra("type"));
        duration.setText(getIntent().getStringExtra("duration"));
        setOrderID(getIntent().getStringExtra("orderID"));

        String passedUsername = getIntent().getStringExtra("username");
        String passedPhoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(OrderConfirmation.this, MainActivity.class);
                intent.putExtra("username", passedUsername);
                intent.putExtra("phoneNo", passedPhoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        rootNode = FirebaseDatabase.getInstance();
        ref = rootNode.getReference("orders");

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child(getOrderID()).removeValue();
                address.setText("cancelled");
                date.setText("cancelled");
                paymentMethod.setText("cancelled");
                orderType.setText("cancelled");
                duration.setText("cancelled");
            }
        });

    }


    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderID() {
        return orderID;
    }

}