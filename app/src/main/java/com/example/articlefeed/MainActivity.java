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


        websiteList = new ArrayList<>();



        websiteRecyclerView = findViewById(R.id.website_recycler_view);
        websiteRecyclerView.addItemDecoration(new DividerItemDecoration(this,0));
        websiteRecyclerView.setHasFixedSize(true);
        websiteLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        websiteAdapter = new WebsiteListAdapter(websiteList,this);

        websiteRecyclerView.setLayoutManager(websiteLayoutManager);
        websiteRecyclerView.setAdapter(websiteAdapter);


        ArrayList<ArticleItem> mekomitArticles = new ArrayList<>();
        ArrayList<ArticleItem> hamakomArticles = new ArrayList<>();
        ArrayList<ArticleItem> haaretzArticles = new ArrayList<>();
        ArrayList<ArticleItem> the7EyeArticles = new ArrayList<>();
        ArrayList<ArticleItem> davidsonArticles= new ArrayList<>();
        ArrayList<ArticleItem> hayadaanArticles= new ArrayList<>();
        ArrayList<ArticleItem> friendsOfGeorgeDownloaderArticles = new ArrayList<>();
        websiteList.add(new WebsiteItem("    הידען  ",hayadaanArticles));
        websiteList.add(new WebsiteItem("    מכון דוידסון  ",davidsonArticles));
        websiteList.add(new WebsiteItem("    העין השביעית  ",the7EyeArticles));
        websiteList.add(new WebsiteItem("    הארץ  ",haaretzArticles));
        websiteList.add(new WebsiteItem("    המקום הכי חם בגיהנום  ",hamakomArticles));
        websiteList.add(new WebsiteItem("    שיחה מקומית  ",mekomitArticles));
        websiteList.add(new WebsiteItem("    החברים של ג'ורג'  ",friendsOfGeorgeDownloaderArticles));
        websiteAdapter.notifyDataSetChanged();

        new  HayadaanDownloader(hayadaanArticles)  {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                hayadaanArticles.addAll(articleList);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hayadan.org.il/feed/");


        new  DavidsonDownloader(davidsonArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                davidsonArticles.addAll(articleList);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://davidson.weizmann.ac.il/");

        new  The7EyeDownloader(the7EyeArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                the7EyeArticles.addAll(articleList);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.the7eye.org.il/");


        new HaaretzDownloader(haaretzArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                haaretzArticles.addAll(articleItems);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.haaretz.co.il/cmlink/1.1617539");


        new HaMakomDownloader(hamakomArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                hamakomArticles.addAll(articleItems);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.ha-makom.co.il/feed/");



        new MekomitDownloader(mekomitArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                mekomitArticles.addAll(articleItems);
                websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.mekomit.co.il/feed/");



        new FriendsOfGeorgeDownloader(){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);
                friendsOfGeorgeDownloaderArticles.addAll(articleItems);
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