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
            String url = "https://www.hayadan.org.il/feed/";
            Document webpage = Jsoup.connect(url).get();
            Document doc = Jsoup.parse(
                    Parser.unescapeEntities(webpage.toString(), false), "",
                    Parser.xmlParser());

            Elements articleElements = doc.select("item");
            int counter = 0;
            for (Element e : articleElements) {
//

                String rawLinkURL = e.select("link").text();    // this returns big string which has to be cut to url only. it begins with the right url and ends with " " or "#"
                int indexOfFirstSpace = rawLinkURL.indexOf(" ", 0);
                int indexOfFirstHashtag = rawLinkURL.indexOf("#",0);
                int indexOfFirstHttps = rawLinkURL.indexOf("https",1);

                int cutFromMinimum = 0;

                if (indexOfFirstHashtag == -1)
                    indexOfFirstHashtag = 9999;

                if (indexOfFirstSpace == -1)
                    indexOfFirstSpace = 9999;

                if (indexOfFirstHttps == -1)
                    indexOfFirstHttps = 9999;


                cutFromMinimum = Math.min(Math.min(indexOfFirstHashtag,indexOfFirstSpace),Math.min(indexOfFirstHttps,indexOfFirstHashtag));

                if(cutFromMinimum == 9999)
                {
                    Log.d("haya","problem...");
                }
                linkUrl = rawLinkURL.substring(0,cutFromMinimum);

//                Elements rawLinkUrlElement = e.select("link");
//                String commentsToRemove = rawLinkUrlElement.select("comments").text();
//                int cutFrom = rawLinkURL.indexOf(commentsToRemove, 0);
//                linkURL = rawLinkURL.substring(0, cutFrom);
//                if (linkURL.equals(""))
//                    linkURL = rawLinkURL;

//                articleURLS.add(linkURL);
                articleURLS.add(linkUrl);
//


            }
//            articleURLS.clear();
//            articleURLS.add("https://www.hayadan.org.il/%d7%a6%d7%99-%d7%94%d7%a2%d7%aa%d7%99%d7%93-%d7%a8%d7%95%d7%91%d7%95%d7%98%d7%99%d7%9d-%d7%92%d7%93%d7%95%d7%9c%d7%99%d7%9d-%d7%95%d7%a7%d7%98%d7%a0%d7%99%d7%9d");
//            articleURLS.add("https://www.hayadan.org.il/%D7%9E%D7%92%D7%9C%D7%99-%D7%A0%D7%92%D7%99%D7%A3-%D7%A6%D7%94%D7%91%D7%AA-c-%D7%96%D7%9B%D7%95-%D7%91%D7%A4%D7%A8%D7%A1-%D7%A0%D7%95%D7%91%D7%9C-%D7%9C%D7%A8%D7%A4%D7%95%D7%90%D7%94-%D7%A9%D7%99");
//            articleURLS.add("https://www.hayadan.org.il/%d7%9c%d7%a7%d7%a4%d7%95%d7%90-%d7%a2%d7%9d-%d7%94%d7%96%d7%a8%d7%9d-%d7%9e%d7%a1%d7%a2-%d7%94%d7%a7%d7%a1%d7%9d-%d7%94%d7%9e%d7%93%d7%a2%d7%99-%d7%97%d7%93%d7%a9%d7%95%d7%aa-%d7%9e%d7%93%d7%a2-2");
//            articleURLS.add("https://www.hayadan.org.il/%d7%a4%d6%bc%d6%b0%d7%92%d6%b4%d7%99%d7%a2%d7%95%d6%bc%d7%aa-%d7%a0%d7%a8%d7%9b%d7%a9%d7%aa-%d7%9e%d7%a1%d7%a2-%d7%94%d7%a7%d7%a1%d7%9d-%d7%94%d7%9e%d7%93%d7%a2%d7%99-%d7%97%d7%93%d7%a9%d7%95-2");
//            articleURLS.add("https://www.hayadan.org.il/%d7%a1%d7%95%d7%9b%d7%95%d7%aa-%d7%91%d7%a1%d7%99%d7%9e%d7%9f-%d7%97%d7%9c%d7%9c-%d7%90%d7%99%d7%a8%d7%95%d7%a2%d7%99-%d7%a9%d7%91%d7%95%d7%a2-%d7%94%d7%97%d7%9c%d7%9c-%d7%94%d7%a2%d7%95%d7%9c%d7%9e");
//            articleURLS.add("https://www.hayadan.org.il/%d7%90%d7%a8%d7%91%d7%a2%d7%aa-%d7%94%d7%a2%d7%95%d7%9c%d7%9e%d7%95%d7%aa-%d7%94%d7%9b%d7%99-%d7%9e%d7%91%d7%98%d7%99%d7%97%d7%99%d7%9d-%d7%9c%d7%a7%d7%99%d7%95%d7%9d-%d7%97%d7%99%d7%99%d7%9d-%d7%97");
//            articleURLS.add("https://www.hayadan.org.il/%d7%a4%d7%99%d7%9c%d7%99%d7%9d-%d7%9e%d7%aa%d7%99%d7%9d-%d7%91%d7%a1%d7%aa%d7%a8");

            Log.d("hayaadan","finished URLS");
            for (int i = 0; i < articleURLS.size(); i++)
            {
                long interval = System.currentTimeMillis();
                String articleURL = articleURLS.get(i);
                Document articlePage = Jsoup.connect(articleURL).get();
                Elements articlePartsElements = articlePage.select("body > div > div > section > div > div > main > div");

               title =  articlePartsElements.select("div.elementor-widget-container").eq(1).select("h1").text();
               body = articlePartsElements.select("div.elementor-widget-container").eq(3).select("p").select("strong").eq(0).text();
               imageURL =  articlePartsElements.select("div.elementor-widget-container").select("figure").select("img").attr("src");
               date = articlePartsElements.select("div.elementor-widget-container").eq(2).select("ul").select("li").eq(1).select("a").select("span").text();


               articles.add(new ArticleItem(title,body,date,imageURL,articleURLS.get(i)));
                Log.d("hayaadan","one article interval "+(System.currentTimeMillis() - interval)/1000 +" seconds");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return articles;
    }


}
