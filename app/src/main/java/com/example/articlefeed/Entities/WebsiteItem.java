package com.example.articlefeed.Entities;

import com.example.articlefeed.Adapters.ArticleListAdapter;

import java.util.ArrayList;

public class WebsiteItem {
    private String websiteName;
    private ArrayList<ArticleItem> articleList;
    private ArticleListAdapter articleListAdapter;

    public WebsiteItem(String websiteName, ArrayList<ArticleItem> articleList) {
        this.websiteName = websiteName;
        this.articleList = articleList;
    }

    public ArticleListAdapter getArticleListAdapter() {
        return articleListAdapter;
    }

    public void setArticleListAdapter(ArticleListAdapter articleListAdapter) {
        this.articleListAdapter = articleListAdapter;
    }

    public ArrayList<ArticleItem> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleItem> articleList) {
        this.articleList = articleList;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }
}
