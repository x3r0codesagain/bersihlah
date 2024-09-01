package com.myapp.bersihlah.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.bersihlah.ArticleContent;
import com.myapp.bersihlah.R;
import com.myapp.bersihlah.model.ArticleModel;


import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<ArticleModel> articleModels;

    public ArticleRecyclerAdapter(Context context, List<ArticleModel> articleModels) {
        this.context = context;
        this.articleModels = articleModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        ArticleModel articleModel = articleModels.get(position);

        holder.articleName.setText(articleModel.getArticleName());
        holder.source.setText(articleModel.getSource());

        holder.articleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ArticleContent.class);
                i.putExtra("title", articleModels.get(position).getArticleName());
                i.putExtra("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                context.startActivity(i);
            }
        });


    }



    @Override
    public int getItemCount() {
        return articleModels.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{


        TextView articleName, source;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            articleName = itemView.findViewById(R.id.articleTitle);
            source = itemView.findViewById(R.id.articleSource);

        }
    }

}
