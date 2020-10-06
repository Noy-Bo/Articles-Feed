package com.example.articlefeed.WebServices;


import android.os.AsyncTask;
import android.util.Log;

import com.example.articlefeed.Entities.ArticleItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HaaretzDownloader extends AsyncTask<String,Void, ArrayList<ArticleItem>> {

    private ArrayList<ArticleItem> articles;

    public HaaretzDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
    }


    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings) {



        String title;
        String body;
        String date;
        String imageURL;
        String linkUrl;

        try {
            String url = strings[0]; // this is an xml, we have to parse it with xml parser.
            Document webpage = Jsoup.connect(url).get();
            Document doc = Jsoup.parse(
                    Parser.unescapeEntities(webpage.toString(), false), "",
                    Parser.xmlParser()); // xml parser.

            Elements articleElements = doc.select("item"); // getting item elements.

            int numOfArticlesToFetch = 20;

            for (Element e : articleElements) {

                title = e.select("title").text();
                body = e.select("description").text();
                date = e.select("pubDate").text();
                linkUrl = e.select("link").text();
                imageURL = e.select("enclosure").attr("url");



                articles.add(new ArticleItem(title,body,date,imageURL,linkUrl));

                if (--numOfArticlesToFetch == 0)
                    break;
            }



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articles;
    }
}
