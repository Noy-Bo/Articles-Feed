package com.example.articlefeed.WebServices;

import android.os.AsyncTask;
import android.util.Log;

import com.example.articlefeed.Entities.ArticleItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FriendsOfGeorgeDownloader extends AsyncTask<String,Void, ArrayList<ArticleItem>>{

    private ArrayList<ArticleItem> articles = new ArrayList<>();

//    public FriendsOfGeorgeDownloader(ArrayList<ArticleItem> articles) {
//        this.articles = articles;
//    }


    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings) {

        //selectors to get articles on webpage.
        String articleSelector = "div.post";

        String title;
        String body;
        String date;
        String linkURL;
        try{
            String url = strings[0];
            Document page = Jsoup.connect(url).get();

            Elements articleElements = page.select(articleSelector);
            for (Element e : articleElements)
            {
                title = e.select("h1 > a").text();
                body = e.select("div.entry > p").eq(0).text();
                date = e.select("div.postmeta > span.post-calendar").text();
                linkURL = e.select(" h1 > a").attr("href");


                //Log.d("george","title: "+linkURL);

                articles.add(new ArticleItem(title,body,date,"https://i.ibb.co/T2NnHsW/clipart362208.png",linkURL)); // no image :(
            }


            return articles;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articles;
    }
}
