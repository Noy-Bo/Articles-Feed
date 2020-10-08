package com.example.articlefeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.articlefeed.Activities.ArticleWebView;
import com.example.articlefeed.ArticleWebViewClient;
import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ArticleListAdapter extends RecyclerView.Adapter <ArticleListAdapter.ArticleViewHolder> {
    private  ArrayList<ArticleItem> articleList;
    private ArticleViewHolder.OnArticleListener mOnArticleListener;
    public Context context;



    public ArrayList<ArticleItem> getArticleList()
    {
        return articleList;
    }

    public ArticleListAdapter(ArrayList<ArticleItem> articleList, ArticleViewHolder.OnArticleListener onArticleListener,Context context) {
        this.articleList = articleList;
        this.mOnArticleListener = onArticleListener;
        this.context = context;

    }

            public static class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                public WebView webView;
                public Context context;
                public ArrayList<ArticleItem> articleList;
                OnArticleListener onArticleListener;
                public TextView articleTitle;
                public TextView articleBody;
                public TextView articleReleaseDate;
                public ImageView articlePicture;
                public Button shareButton;
                public CardView cardView;

                public ArticleViewHolder(@NonNull View itemView, OnArticleListener onArticleListener,ArrayList<ArticleItem> articleList,Context context) {
                    super(itemView);
                    this.context = context;
                    // get field boxes via XML.
                    this.articleList = articleList;
                    articleBody = itemView.findViewById(R.id.article_body);
                    articleTitle = itemView.findViewById(R.id.article_title);
                    articleReleaseDate = itemView.findViewById(R.id.article_date);
                    articlePicture = itemView.findViewById(R.id.article_image);
                    shareButton = itemView.findViewById(R.id.share_article_button);
                    cardView = itemView.findViewById(R.id.article_card_view);

                    shareButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("testing clicks","clicked "+ articleList.get(getAdapterPosition()).getArticleTitle());
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody = articleList.get(getAdapterPosition()).getArticleLinkURL();
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(context,Intent.createChooser(sharingIntent, "Share via"),null);
                        }
                    });
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("testing clicks","CARD");
//                            Intent browseArticleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleList.get(getAdapterPosition()).getArticleLinkURL()));
//                            startActivity(context,browseArticleIntent,null);
                                Intent websiteIntent = new Intent(context,ArticleWebView.class);
                                websiteIntent.putExtra("url",articleList.get(getAdapterPosition()).getArticleLinkURL());
                                startActivity(context,websiteIntent,null);


                        }
                    });


                    this.onArticleListener = onArticleListener;


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
        ArticleListAdapter.ArticleViewHolder articleViewHolder = new ArticleListAdapter.ArticleViewHolder(view,mOnArticleListener,articleList,context);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleItem currentItem = articleList.get(position);

        holder.articleTitle.setText(currentItem.getArticleTitle());
        holder.articleBody.setText(currentItem.getArticleBody());

        if (currentItem.getArticlePictureURL() != null && currentItem.getArticlePictureURL().startsWith("https://"))
            Picasso.get().load(currentItem.getArticlePictureURL()).into(holder.articlePicture);
        else
            Picasso.get().load("https://i.ibb.co/4p6B1NM/rsz-newspaper-976110-640.png").into(holder.articlePicture);
        holder.articleReleaseDate.setText(currentItem.getArticleReleaseDate());


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }



}
