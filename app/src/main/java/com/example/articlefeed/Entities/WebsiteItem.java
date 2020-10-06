package com.example.articlefeed.Entities;

import java.util.ArrayList;

public class WebsiteItem {
    private String websiteName;
    private ArrayList<ArticleItem> articleList;

    public WebsiteItem(String websiteName, ArrayList<ArticleItem> articleList) {
        this.websiteName = websiteName;
        this.articleList = articleList;
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
