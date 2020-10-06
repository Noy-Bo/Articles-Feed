package com.example.articlefeed.WebServices;

import android.os.AsyncTask;

import com.example.articlefeed.Entities.ArticleItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HayadaanImageDownloader extends AsyncTask<Void,Void, Void> {
    public HayadaanImageDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
    }
    private ArrayList<ArticleItem> articles;

    @Override
    protected Void doInBackground(Void... voids) {

        ArrayList<String> articleURLS = new ArrayList<>();


        String imageURL;

        try {

            for (int i = 0; i < articles.size(); i++)
            {
                long interval = System.currentTimeMillis();
                String articleURL = articles.get(i).getArticleLinkURL();
                Document articlePage = Jsoup.connect(articleURL).get();
                Elements articlePartsElements = articlePage.select("body > div > div > section > div > div > main > div");

               imageURL =  articlePartsElements.select("div.elementor-widget-container").select("figure").select("img").attr("src");
               articles.get(i).setArticlePictureURL(imageURL);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }


}
