package com.example.articlefeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.articlefeed.Adapters.WebsiteListAdapter;
import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Entities.WebsiteItem;
import com.example.articlefeed.WebServices.DavidsonDownloader;
import com.example.articlefeed.WebServices.FriendsOfGeorgeDownloader;
import com.example.articlefeed.WebServices.HaMakomDownloader;
import com.example.articlefeed.WebServices.HaaretzDownloader;
import com.example.articlefeed.WebServices.HayadaanDownloader;
import com.example.articlefeed.WebServices.HayadaanImageDownloader;
import com.example.articlefeed.WebServices.MekomitDownloader;
import com.example.articlefeed.WebServices.The7EyeDownloader;
import com.example.articlefeed.WebServices.checker;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView websiteRecyclerView;
    private RecyclerView.Adapter websiteAdapter;
    private RecyclerView.LayoutManager websiteLayoutManager;

    private RecyclerView articleRecyclerView;
    private RecyclerView.Adapter articleAdapter;
    private RecyclerView.LayoutManager articleLayoutManager;
    private  ArrayList<WebsiteItem> websiteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

      /*  ArrayList<ArticleItem> articleList = new ArrayList<>();
       articleList.add(new ArticleItem( "כתבה מספר 1", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem( "כתבה מספר 2", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem( "כתבה מספר 3", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem( "כתבה מספר 4", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem( "כתבה מספר 5", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem( "כתבה מספר 6", "בלהבלהבלהבלהבלהבלה", null,null,null));
       articleList.add(new ArticleItem(  "כתבה מספר 7", "בלהבלהבלהבלהבלהבלה", null,null,null));*/

        websiteList = new ArrayList<>();
       /* websiteList.add(new WebsiteItem("  אתר מספר 1  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 2  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 3  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 4  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 5  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 6  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 7  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 8  ",articleList));
        websiteList.add(new WebsiteItem("  אתר מספר 9  ",articleList));*/


        websiteRecyclerView = findViewById(R.id.website_recycler_view);
        websiteRecyclerView.addItemDecoration(new DividerItemDecoration(this,0));
        //websiteRecyclerView.setHasFixedSize(true);
        websiteLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        websiteAdapter = new WebsiteListAdapter(websiteList,this);

        websiteRecyclerView.setLayoutManager(websiteLayoutManager);
        websiteRecyclerView.setAdapter(websiteAdapter);



        ArrayList<ArticleItem> hayadaanArticles= new ArrayList<>();
        new  HayadaanDownloader(hayadaanArticles)  {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                websiteList.add(new WebsiteItem("    הידען  ",articleList));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hayadan.org.il/feed/");

        ArrayList<ArticleItem> davidsonArticles= new ArrayList<>();
        new  DavidsonDownloader(davidsonArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                websiteList.add(new WebsiteItem("    מכון דוידסון  ",articleList));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://davidson.weizmann.ac.il/");

        ArrayList<ArticleItem> the7EyeArticles = new ArrayList<>();
        new  The7EyeDownloader(the7EyeArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                websiteList.add(new WebsiteItem("    העין השביעית  ",articleList));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.the7eye.org.il/");

        ArrayList<ArticleItem> haaretzArticles = new ArrayList<>();
        new HaaretzDownloader(haaretzArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                websiteList.add(new WebsiteItem("    הארץ  ",articleItems));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.haaretz.co.il/cmlink/1.1617539");

        ArrayList<ArticleItem> hamakomArticles = new ArrayList<>();
        new HaMakomDownloader(hamakomArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                websiteList.add(new WebsiteItem("    המקום הכי חם בגיהנום  ",articleItems));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.ha-makom.co.il/feed/");


        ArrayList<ArticleItem> mekomitArticles = new ArrayList<>();
        new MekomitDownloader(mekomitArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                websiteList.add(new WebsiteItem("    שיחה מקומית  ",articleItems));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.mekomit.co.il/feed/");


        ArrayList<ArticleItem> friendsOfGeorgeDownloaderArticles = new ArrayList<>();
        new FriendsOfGeorgeDownloader(){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                websiteList.add(new WebsiteItem("    החברים של ג'ורג'  ",articleItems));
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hahem.co.il/friendsofgeorge/");


        new HayadaanImageDownloader(hayadaanArticles){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                websiteAdapter.notifyDataSetChanged();

            }
        }.execute();




    }


}