package com.example.articlefeed.WebServices;

import android.os.AsyncTask;
import android.util.Log;


import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Utilities.DateParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


public class UrbanologiaDownloader extends AsyncTask<String,Void,ArrayList<ArticleItem>>{


    private ArrayList<ArticleItem> articles;

    public UrbanologiaDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
    }
    //private ArrayList<ArticleItem> refreshedArticles = new ArrayList<>();


    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings) {

        String title;
        String body;
        String date;
        String imageURL;
        String linkURL;
        try{
            String url = strings[0];
            Document page = Jsoup.connect(url).get();
            Elements test = page.select("body");
            Elements test1 = test.select("div#cb-outer-container > div#cb-container > section#cb-section-a> div.cb-grid-block.cb-module-block.cb-s-5.clearfix div > div");
            Elements test2 = test.select("div#cb-outer-container > div#cb-container > section#cb-section-a > div.cb-grid-block.cb-module-block.cb-s-5.clearfix > div > div:first-child");

            String articleSelector = "div#cb-outer-container > div#cb-container > section#cb-section-a > div.cb-grid-block.cb-module-block.cb-s-5.clearfix > div > div > ul > li";
            Elements articleElements = page.select(articleSelector);


            for (Element e : articleElements) {
                Elements trio = e.select("ul > li");
                for (Element article : trio) {
                    title = e.select("div.cb-article-meta").select("h2").select("a").text();
                    body = e.select("p").attr("data-field-homepage-short-text");
                    date = e.select("div.info").select("span.date").text();
                    imageURL = e.select("a").select("div.imgBg").attr("data-field-square-picture");
                    linkURL = e.select("a").attr("href");

                    if (!title.equals("מה קורה?")) // weird davidson feed that needs to be removed
                    {
                        articles.add(new ArticleItem(title, body, date, imageURL, linkURL));
                    }
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articles;
    }

}
