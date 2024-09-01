package com.myapp.bersihlah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.myapp.bersihlah.adapter.BannerItemsAdapter;
import com.myapp.bersihlah.model.BannerItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager bannerViewPager;

    TabLayout indicatorTab;

    List<BannerItems> bannerItems;

    BannerItemsAdapter bannerItemsAdapter;

    String username1;

    ImageView allInOne, bedroom, livingRoom, toilet, kitchen, homeFaq, profilePic, homeOrderList, articleButton;

    TextView welcomeText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tabIndicator);

        welcomeText = findViewById(R.id.welcomeText);

        allInOne = findViewById(R.id.allinone);
        livingRoom = findViewById(R.id.livingRoom);
        toilet = findViewById(R.id.toilet);
        kitchen = findViewById(R.id.kitchen);
        bedroom = findViewById(R.id.bedroom);

        homeFaq = findViewById(R.id.homeFaq);

        profilePic = findViewById(R.id.profilePic);

        articleButton = findViewById(R.id.homeArticle);


        homeOrderList = findViewById(R.id.homeOrderList);


        username1 = getIntent().getStringExtra("username");
        String phoneNo = getIntent().getStringExtra("phoneNo");
        String email = getIntent().getStringExtra("email");






        welcomeText.setText("Welcome " + username1);

        bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItems(1, "Banner1", "https://img.freepik.com/free-vector/home-cleaning-service-flat-vector-promo-banner_81534-2294.jpg?w=2000"));
        bannerItems.add(new BannerItems(2, "Banner2", "https://www.dropbox.com/s/gj6mshb6hr8wbsg/Group%2034.png?dl=1"));
        bannerItems.add(new BannerItems(3, "Banner3", "https://www.dropbox.com/s/9c65x5tmqv6ly0i/Group%2034%20%281%29.png?dl=1"));
        bannerItems.add(new BannerItems(4, "Banner4", "https://www.dropbox.com/s/prknpxd6v2bzhtc/Group%2034%20%282%29.png?dl=1"));
        bannerItems.add(new BannerItems(5, "Banner5", "https://img.freepik.com/free-photo/side-view-close-up-hand-young-man-apron-rubber-gloves-holding-basket-cleaning-equipment-feather-duster-spray-bottle-sponge-cloth-wiping-basket_1150-48118.jpg?t=st=1654325135~exp=1654325735~hmac=d0e48554e23f0136ed222d8b6688828557bfdf91aadd61cc4c1ded1c479ea8d9&w=826"));
        bannerItems.add(new BannerItems(6, "Banner6", "https://img.freepik.com/free-photo/young-girl-is-holding-cleaning-product-gloves-rags-basin-white-wall_1150-21780.jpg?w=826&t=st=1654348273~exp=1654348873~hmac=624b7378cbbccb8043ce5cb3c6830d54c119afc59506dc77938e9195c8214220"));

        setBannerAdapter(bannerItems);

        homeOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderList.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        homeFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Faq.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        allInOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "All in One");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        articleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Article.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "All in One");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "Bedroom Only");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        livingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "Living Room Only");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Order.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "Kitchen Only");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Order.class);
                intent.putExtra("username", username1);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("type", "Toilet Only");
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });








    }



    private void setBannerAdapter(List<BannerItems> bannerItems){

        bannerViewPager = findViewById(R.id.bannerViewPager);
        bannerItemsAdapter = new BannerItemsAdapter(this, bannerItems);
        bannerViewPager.setAdapter(bannerItemsAdapter);
        indicatorTab.setupWithViewPager(bannerViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerViewPager, true);

    }

    class AutoSlider extends TimerTask {


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bannerViewPager.getCurrentItem() < bannerItems.size()-1){

                        bannerViewPager.setCurrentItem(bannerViewPager.getCurrentItem()+1);

                    }
                    else{

                        bannerViewPager.setCurrentItem(0);

                    }
                }
            });

        }

    }
}