package com.example.articlefeed.WebServices;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


import com.example.articlefeed.Entities.ArticleItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DavidsonDownloader extends AsyncTask<String,Void,ArrayList<ArticleItem>> {
    public DavidsonDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
    }

    private ArrayList<ArticleItem> articles;


    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings)  {

       String title;
        String body;
        String date;
        String imageURL;
        String linkURL;
        try{
            String url = strings[0];
            Document page = Jsoup.connect(url).get();

            String articleSelector = "body > div.main > div > div > div.item";
            Elements articleElements = page.select(articleSelector);


            for (Element e : articleElements)
            {
                title = e.select("h3").text();
                body = e.select("p").attr("data-field-homepage-short-text");
                date = e.select("div.info").select("span.date").text();
                imageURL = e.select("a").select("div.imgBg").attr("data-field-square-picture");
                linkURL = e.select("a").attr("href");

                if (!title.equals("מה קורה?") ) // weird davidson feed that needs to be removed
                {
                    articles.add(new ArticleItem(title, body, date, imageURL, linkURL));
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
