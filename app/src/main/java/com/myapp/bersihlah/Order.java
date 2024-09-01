package com.myapp.bersihlah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.bersihlah.model.OrderHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order extends AppCompatActivity {

    TextInputLayout addressInput, hourInput, minuteInput, paymentMethodInput;
    TextView totalPrice;
    ImageView backButton;
    Button orderButton, setTimeButton;
    private String passedUsername;
    private String serviceType;
    private String passedPhoneNo;

    FirebaseDatabase rootNode;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        addressInput = findViewById(R.id.addressInput);
        hourInput = findViewById(R.id.hourInput);
        minuteInput = findViewById(R.id.minuteInput);
        paymentMethodInput = findViewById(R.id.paymentInput);
        orderButton = findViewById(R.id.buttonOrder);
        setTimeButton = findViewById(R.id.setTime);
        backButton = findViewById(R.id.back);

        rootNode = FirebaseDatabase.getInstance();
        ref = rootNode.getReference("orders");
        totalPrice = findViewById(R.id.totalPrice);

        setServiceType(getIntent().getStringExtra("type"));
        setPassedUsername(getIntent().getStringExtra("username"));
        setPassedPhoneNo(getIntent().getStringExtra("phoneNo"));

        String email = getIntent().getStringExtra("email");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order.this, MainActivity.class);
                intent.putExtra("username", passedUsername);
                intent.putExtra("phoneNo", passedPhoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerOrder(view);

            }
        });

        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hour = hourInput.getEditText().getText().toString();
                String minute = minuteInput.getEditText().getText().toString();
                String price="0";
                validateMinute();
                validateHour();

                if(validateMinute() && validateHour()) {
                    if(minute.isEmpty()){
                        minute = "0";
                    }
                    if(hour.isEmpty()){
                        hour = "0";
                    }
                    if (serviceType.equals("All in One")) {
                        price = Integer.toString((int) Integer.parseInt(hour) * 100000 + Integer.parseInt(minute) * 50000 / 30);
                    } else {
                        price = Integer.toString((int) Integer.parseInt(hour) * 60000 + Integer.parseInt(minute) * 30000 / 30);
                    }
                }


                totalPrice.setText("Total Price:Rp. " + price);
            }
        });
    }

    public String getPassedUsername() {
        return passedUsername;
    }

    public void setPassedUsername(String passedUsername) {
        this.passedUsername = passedUsername;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPassedPhoneNo() {
        return passedPhoneNo;
    }

    public void setPassedPhoneNo(String passedPhoneNo) {
        this.passedPhoneNo = passedPhoneNo;
    }

    public boolean validateAddress(){
        String address = addressInput.getEditText().getText().toString();
        if(address.isEmpty()){
            addressInput.setError("Please fill in your address");
            return false;
        }else{
            addressInput.setError(null);
            addressInput.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateHour(){
        String hour = hourInput.getEditText().getText().toString();
        String minute = minuteInput.getEditText().getText().toString();

        if(!minute.isEmpty() && hour.isEmpty() && checkIfNum(minute)){
            hour = "0";
        }
        if(((hour.isEmpty() && minute.isEmpty())||!checkIfNum(hour))||Integer.parseInt(hour)>5){
            hourInput.setError("Please fill in your hour (max 5)");
            return false;
        }else{
            hourInput.setError(null);
            hourInput.setErrorEnabled(false);
            return true;
        }


    }

    public boolean checkIfNum(String check){
        try {
            Integer.parseInt(check);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean validateMinute(){
        String minute = minuteInput.getEditText().getText().toString();
        String hour = hourInput.getEditText().getText().toString();

        if(!hour.isEmpty() && minute.isEmpty() && checkIfNum(hour)){
            minute = "0";
        }
        if(((minute.isEmpty() && hour.isEmpty())|!checkIfNum(minute))||Integer.parseInt(minute)>59){
            minuteInput.setError("Please fill in your minute with numbers max(59)");
            return false;
        }
        else{
            minuteInput.setError(null);
            minuteInput.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validatePayment(){
        String paymentMethod = paymentMethodInput.getEditText().getText().toString();
        if(paymentMethod.isEmpty()){
            paymentMethodInput.setError("Please fill in your payment");
            return false;
        }else if(!paymentMethod.equals("GoPay") && !paymentMethod.equals("OVO") && !paymentMethod.equals("Bank Tfr")){
            paymentMethodInput.setError("Payment method not available");
            return false;
        }else{
            paymentMethodInput.setError(null);
            paymentMethodInput.setErrorEnabled(false);
            return true;
        }
    }

    public void registerOrder(View v){
        if(!validateAddress() | !validateHour() | !validateMinute() | !validatePayment()){
            return;
        }

        String address = addressInput.getEditText().getText().toString();
        String hour = hourInput.getEditText().getText().toString();
        String minute = minuteInput.getEditText().getText().toString();
        String paymentMethod = paymentMethodInput.getEditText().getText().toString();
        String orderID = getPassedUsername()+getPassedPhoneNo()+ (int)(Math.random()*10);
        String price = "0";

        if(!hour.isEmpty() || !minute.isEmpty()) {
            if(minute.isEmpty()){
                minute = "0";
            }
            if(hour.isEmpty()){
                hour = "0";
            }
            if (serviceType.equals("All in One")) {
                price = Integer.toString((int) Integer.parseInt(hour) * 100000 + Integer.parseInt(minute) * 50000 / 30);
            } else {
                price = Integer.toString((int) Integer.parseInt(hour) * 60000 + Integer.parseInt(minute) * 30000 / 30);
            }
        }

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String email = getIntent().getStringExtra("email");

        OrderHelper orderData = new OrderHelper(getPassedUsername(), getServiceType(), getPassedPhoneNo(), orderID, address, hour, minute, price, paymentMethod, date);

        ref.child(orderID).setValue(orderData);

        Intent intent = new Intent(Order.this, OrderConfirmation.class);
        intent.putExtra("address", address);
        intent.putExtra("orderID", orderID);
        intent.putExtra("type", getServiceType());
        intent.putExtra("paymentMethod", paymentMethod);
        intent.putExtra("duration", hour+" hrs "+minute+" mins");
        intent.putExtra("date", date);
        intent.putExtra("username", passedUsername);
        intent.putExtra("phoneNo", passedPhoneNo);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}