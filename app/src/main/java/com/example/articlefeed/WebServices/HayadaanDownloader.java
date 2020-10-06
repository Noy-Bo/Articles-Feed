package com.example.articlefeed.WebServices;

import android.os.AsyncTask;
import android.util.Log;

import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class HayadaanDownloader extends AsyncTask<String,Void, ArrayList<ArticleItem>> {
    public HayadaanDownloader(ArrayList<ArticleItem> articles) {
        this.articles = articles;
    }

    private ArrayList<ArticleItem> articles;

    @Override
    protected ArrayList<ArticleItem> doInBackground(String... strings) {

        ArrayList<String> articleURLS = new ArrayList<>();

        String title;
        String body;
        String date;
        String imageURL;
        String linkUrl = "no url found.";

        try {
            String url = "https://www.hayadan.org.il/feed/"; // this is an xml, we have to parse it with xml parser.
            Document webpage = Jsoup.connect(url).get();
            Document doc = Jsoup.parse(
                    Parser.unescapeEntities(webpage.toString(), false), "",
                    Parser.xmlParser()); // xml parser.

            Elements articleElements = doc.select("item"); // getting item elements.

            for (Element e : articleElements) {

                String rawLinkURL = e.select("link").text();    // this returns big string which has to be cut to url only. it begins with the right url and ends with " " or "#"
                int indexOfFirstSpace = rawLinkURL.indexOf(" ", 0);
                int indexOfFirstHashtag = rawLinkURL.indexOf("#",0);
                int indexOfFirstHttps = rawLinkURL.indexOf("https",1);

                int cutUpToThisNumber = 0;

                if (indexOfFirstHashtag == -1)
                    indexOfFirstHashtag = 9999;

                if (indexOfFirstSpace == -1)
                    indexOfFirstSpace = 9999;

                if (indexOfFirstHttps == -1)
                    indexOfFirstHttps = 9999;


                cutUpToThisNumber = Math.min(Math.min(indexOfFirstHashtag,indexOfFirstSpace),Math.min(indexOfFirstHttps,indexOfFirstHashtag));

                if(cutUpToThisNumber == 9999)
                {
                    cutUpToThisNumber = linkUrl.length();
                }
                linkUrl = rawLinkURL.substring(0,cutUpToThisNumber);

                articleURLS.add(linkUrl); // we have list of URLS now.
                date = e.select("pubDate").text();
                body = e.select("description").text();
                title = e.select("title").text();
                articles.add(new ArticleItem(title,body,date,"https://i.ibb.co/T2NnHsW/clipart362208.png",linkUrl));
            }


//            for (int i = 0; i < articleURLS.size(); i++)
//            {
//                long interval = System.currentTimeMillis();
//                String articleURL = articleURLS.get(i);
//                Document articlePage = Jsoup.connect(articleURL).get();
//                Elements articlePartsElements = articlePage.select("body > div > div > section > div > div > main > div");
//
//               title =  articlePartsElements.select("div.elementor-widget-container").eq(1).select("h1").text();
//               body = articlePartsElements.select("div.elementor-widget-container").eq(3).select("p").select("strong").eq(0).text();
//               imageURL =  articlePartsElements.select("div.elementor-widget-container").select("figure").select("img").attr("src");
//               date = articlePartsElements.select("div.elementor-widget-container").eq(2).select("ul").select("li").eq(1).select("a").select("span").text();
//
//
//
//                Log.d("hayaadan","one article interval "+(System.currentTimeMillis() - interval)/1000 +" seconds");
//            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articles;
    }


}
