package com.myapp.bersihlah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.myapp.bersihlah.model.BannerItems;
import com.myapp.bersihlah.R;


import java.util.List;

public class BannerItemsAdapter extends PagerAdapter {

    Context context;
    List<BannerItems> bannerItemsList;

    public BannerItemsAdapter(Context context, List<BannerItems> bannerMoviesList) {
        this.context = context;
        this.bannerItemsList = bannerMoviesList;
    }

    @Override
    public int getCount() {
        return bannerItemsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, null);

        final ImageView bannerImage;
        bannerImage = view.findViewById(R.id.banner_image);


        Glide.with(context).load(bannerItemsList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);


        return view;


    }

}
