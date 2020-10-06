package com.example.articlefeed.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Entities.WebsiteItem;
import com.example.articlefeed.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class ArticleListAdapter extends RecyclerView.Adapter <ArticleListAdapter.ArticleViewHolder> {
    static  ArrayList<ArticleItem> articleList;
    private ArticleViewHolder.OnArticleListener mOnArticleListener;

    public ArrayList<ArticleItem> getArticleList()
    {
        return articleList;
    }

    public ArticleListAdapter(ArrayList<ArticleItem> articleList, ArticleViewHolder.OnArticleListener onArticleListener) {
        this.articleList = articleList;
        this.mOnArticleListener = onArticleListener;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnArticleListener onArticleListener;
        public TextView articleTitle;
        public TextView articleBody;
        public TextView articleReleaseDate;
        public ImageView articlePicture;

        public ArticleViewHolder(@NonNull View itemView, OnArticleListener onArticleListener) {
            super(itemView);
            // get field boxes via XML.
            articleBody = itemView.findViewById(R.id.article_body);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleReleaseDate = itemView.findViewById(R.id.article_date);
            articlePicture = itemView.findViewById(R.id.article_image);
            this.onArticleListener = onArticleListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onArticleListener.onArticleClick(getAdapterPosition(),articleList.get(getAdapterPosition()));

        }

        public interface OnArticleListener{
            void onArticleClick(int position,ArticleItem articleItem);
        }
    }




    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        ArticleListAdapter.ArticleViewHolder articleViewHolder = new ArticleListAdapter.ArticleViewHolder(view,mOnArticleListener);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleItem currentItem = articleList.get(position);

        holder.articleTitle.setText(currentItem.getArticleTitle());
        holder.articleBody.setText(currentItem.getArticleBody());

        if (currentItem.getArticlePictureURL() != null && currentItem.getArticlePictureURL().startsWith("https://"))
            Picasso.get().load(currentItem.getArticlePictureURL()).into(holder.articlePicture);

        holder.articleReleaseDate.setText(currentItem.getArticleReleaseDate());


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }



}
