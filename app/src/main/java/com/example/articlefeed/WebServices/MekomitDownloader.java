package com.example.articlefeed.WebServices;

import android.os.AsyncTask;

import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Utilities.DateParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MekomitDownloader extends AsyncTask<String,Void, ArrayList<ArticleItem>> {

    private ArrayList<ArticleItem> articles;

    public MekomitDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
        this.articles.clear();
    }


    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings) {

        ArrayList<String> articleURLS = new ArrayList<>();

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

                String rawBody = e.select("description").text();
                rawBody = rawBody.substring(3);
                int bodyUrlEndAt = rawBody.indexOf("</p>",0);
                body = rawBody.substring(0,bodyUrlEndAt);

                String rawDate = e.select("pubDate").text();
                date = DateParser.FullDateObjectStringToHebrewDate(rawDate);

                linkUrl = e.select("link").text();

                 String rawImageUrl= e.select("content|encoded").text();
                 int  imageUrlStartAt = rawImageUrl.indexOf("http",1);
                 rawImageUrl = rawImageUrl.substring(imageUrlStartAt);
                 int imageUrlEndAt = rawImageUrl.indexOf("\"",0);
                 imageURL = rawImageUrl.substring(0,imageUrlEndAt);



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
