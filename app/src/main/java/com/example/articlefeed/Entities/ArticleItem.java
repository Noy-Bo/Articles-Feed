package com.example.articlefeed.Entities;

import java.net.URL;
import java.util.Date;

public class ArticleItem {
    private String articleTitle;
    private String articleBody;
    private String articleReleaseDate;
    private String articlePictureURL;
    private String articleLinkURL;

    public ArticleItem(String articleTitle, String articleBody, String articleReleaseDate, String articlePictureURL, String articleLinkURL) {
        this.articleTitle = articleTitle;
        this.articleBody = articleBody;
        this.articleReleaseDate = articleReleaseDate;
        this.articlePictureURL = articlePictureURL;
        this.articleLinkURL = articleLinkURL;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getArticleReleaseDate() {
        return articleReleaseDate;
    }

    public void setArticleReleaseDate(String articleReleaseDate) {
        this.articleReleaseDate = articleReleaseDate;
    }

    public String getArticlePictureURL() {
        return articlePictureURL;
    }

    public void setArticlePictureURL(String articlePictureURL) {
        this.articlePictureURL = articlePictureURL;
    }

    public String getArticleLinkURL() {
        return articleLinkURL;
    }

    public void setArticleLinkURL(String articleLinkURL) {
        this.articleLinkURL = articleLinkURL;
    }
}
