package com.example.articlefeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Entities.WebsiteItem;
import com.example.articlefeed.R;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class WebsiteListAdapter extends RecyclerView.Adapter<WebsiteListAdapter.WebsiteViewHolder> implements ArticleListAdapter.ArticleViewHolder.OnArticleListener {
    private Context context;
    private ArrayList<WebsiteItem> websiteList ;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

            public static class WebsiteViewHolder extends RecyclerView.ViewHolder {


                public TextView websiteName;
                public RecyclerView articleRecyclerView;


                public WebsiteViewHolder(@NonNull View itemView) {
                    super(itemView);

                    websiteName = itemView.findViewById(R.id.website_name);
                    articleRecyclerView = itemView.findViewById(R.id.article_recycler_view);

                }
            }

    public WebsiteListAdapter(ArrayList<WebsiteItem> websiteList,Context context)
    {
        this.context = context;
       this.websiteList = websiteList;
    }

    @NonNull
    @Override
    public WebsiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.website_item,parent,false);
        WebsiteViewHolder websiteViewHolder = new WebsiteViewHolder(view);
        return websiteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteViewHolder holder, int position) {

        // Create an instance of the current WebsiteItem
        // class for the given position
        WebsiteItem currentItem = websiteList.get(position);

        holder.websiteName.setText(currentItem.getWebsiteName());

        //Create layout manager with initial prefetch item count
        LinearLayoutManager articleLayoutManager = new LinearLayoutManager(
                holder.articleRecyclerView.getContext(),LinearLayoutManager.HORIZONTAL,true
        );
        articleLayoutManager.setInitialPrefetchItemCount(currentItem.getArticleList().size());

        //Create sub item view adapter
        ArticleListAdapter articleListAdapter = new ArticleListAdapter(websiteList.get(position).getArticleList(),this);

        holder.articleRecyclerView.setLayoutManager(articleLayoutManager);
        holder.articleRecyclerView.setAdapter(articleListAdapter);
        holder.articleRecyclerView.setRecycledViewPool(viewPool);

        //holder.articleRecyclerView.setNestedScrollingEnabled(false);




    }

    @Override
    public int getItemCount() {
        return websiteList.size();
    }
    @Override
    public void onArticleClick(int position, ArticleItem articleItem) {

        Log.d("test","clicked on "+position + "title: " +articleItem.getArticleTitle());

        Intent browseArticleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.getArticleLinkURL()));
        startActivity(context,browseArticleIntent,null);
    }
}

