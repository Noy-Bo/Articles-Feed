package com.example.articlefeed.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.articlefeed.Adapters.WebsiteListAdapter;
import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Entities.WebsiteItem;
import com.example.articlefeed.R;
import com.example.articlefeed.WebServices.DavidsonDownloader;
import com.example.articlefeed.WebServices.FriendsOfGeorgeDownloader;
import com.example.articlefeed.WebServices.HaMakomDownloader;
import com.example.articlefeed.WebServices.HaaretzDownloader;
import com.example.articlefeed.WebServices.HayadaanDownloader;
import com.example.articlefeed.WebServices.HayadaanImageDownloader;
import com.example.articlefeed.WebServices.MekomitDownloader;
import com.example.articlefeed.WebServices.The7EyeDownloader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView websiteRecyclerView;
    private RecyclerView.Adapter websiteAdapter;
    private RecyclerView.LayoutManager websiteLayoutManager;

    private RecyclerView articleRecyclerView;
    private RecyclerView.Adapter articleAdapter;
    private RecyclerView.LayoutManager articleLayoutManager;
    private  ArrayList<WebsiteItem> websiteList;
    private ArrayList<ArticleItem> mekomitArticles;
    private ArrayList<ArticleItem> hamakomArticles;
    private ArrayList<ArticleItem> haaretzArticles;
    private ArrayList<ArticleItem> the7EyeArticles;
    private ArrayList<ArticleItem> davidsonArticles;
    private ArrayList<ArticleItem> hayadaanArticles;
    ArrayList<ArticleItem> friendsOfGeorgeArticles;

    private WebsiteItem hayadaanWebsiteList;
    private WebsiteItem davidsonWebsiteList;
    private WebsiteItem the7EyeWebsiteList;
    private WebsiteItem haaretzWebsiteList;
    private WebsiteItem hamakomWebsiteList;
    private WebsiteItem mekomitWebsiteList;
    private WebsiteItem friendsOfGeorgeWebsiteList;

    private ImageButton refreshButtonAsImage;
    private Animation animation;
    public  ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading..");
        pd.show();

        refreshButtonAsImage = findViewById(R.id.refresh_button);

        websiteList = new ArrayList<>();

        websiteRecyclerView = findViewById(R.id.website_recycler_view);
        websiteRecyclerView.addItemDecoration(new DividerItemDecoration(this,0));
        websiteRecyclerView.setHasFixedSize(true);
        websiteLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        websiteAdapter = new WebsiteListAdapter(websiteList,this);

        websiteRecyclerView.setLayoutManager(websiteLayoutManager);
        websiteRecyclerView.setAdapter(websiteAdapter);



        mekomitArticles = new ArrayList<>();
        hamakomArticles = new ArrayList<>();
        haaretzArticles = new ArrayList<>();
        the7EyeArticles = new ArrayList<>();
        davidsonArticles = new ArrayList<>();
        hayadaanArticles = new ArrayList<>();
        friendsOfGeorgeArticles = new ArrayList<>();


         hayadaanWebsiteList = new WebsiteItem("    הידען  ",hayadaanArticles);
        websiteList.add(hayadaanWebsiteList);

         davidsonWebsiteList = new WebsiteItem("    מכון דוידסון  ",davidsonArticles);
        websiteList.add(davidsonWebsiteList);

         the7EyeWebsiteList = new WebsiteItem("    העין השביעית  ",the7EyeArticles);
        websiteList.add(the7EyeWebsiteList);

         haaretzWebsiteList = new WebsiteItem("    הארץ  ",haaretzArticles);
        websiteList.add(haaretzWebsiteList);

         hamakomWebsiteList = new WebsiteItem("    המקום הכי חם בגיהנום  ",hamakomArticles);
        websiteList.add(hamakomWebsiteList);

         mekomitWebsiteList = new WebsiteItem("    שיחה מקומית  ",mekomitArticles);
        websiteList.add(mekomitWebsiteList);

         friendsOfGeorgeWebsiteList = new WebsiteItem("    החברים של ג'ורג'  ", friendsOfGeorgeArticles);
        websiteList.add(friendsOfGeorgeWebsiteList);

        websiteAdapter.notifyDataSetChanged();

        RefreshAllData();





    }
    public void RefreshAllData(View view)
    {


        animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(900);



        ((ImageView)refreshButtonAsImage).startAnimation(animation);
        RefreshAllData();

    }

    public void RefreshAllData()
        {
        new  HayadaanDownloader(hayadaanArticles)  {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                if (hayadaanWebsiteList.getArticleListAdapter() != null)
                    hayadaanWebsiteList.getArticleListAdapter().notifyDataSetChanged();

                pd.dismiss();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hayadan.org.il/feed/");


        new  DavidsonDownloader(davidsonArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);

                if (davidsonWebsiteList.getArticleListAdapter() != null)
                    davidsonWebsiteList.getArticleListAdapter().notifyDataSetChanged();


                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://davidson.weizmann.ac.il/");

        new  The7EyeDownloader(the7EyeArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);

                if (the7EyeWebsiteList.getArticleListAdapter() != null)
                    the7EyeWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.the7eye.org.il/");


        new HaaretzDownloader(haaretzArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (haaretzWebsiteList.getArticleListAdapter() != null)
                    haaretzWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.haaretz.co.il/cmlink/1.1470869");


        new HaMakomDownloader(hamakomArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (hamakomWebsiteList.getArticleListAdapter() != null)
                    hamakomWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.ha-makom.co.il/feed/");



        new MekomitDownloader(mekomitArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (mekomitWebsiteList.getArticleListAdapter() != null)
                    mekomitWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.mekomit.co.il/feed/");



        new FriendsOfGeorgeDownloader(friendsOfGeorgeArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (friendsOfGeorgeWebsiteList.getArticleListAdapter() != null)
                    friendsOfGeorgeWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hahem.co.il/friendsofgeorge/");


        new HayadaanImageDownloader(hayadaanArticles){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hayadaanWebsiteList.getArticleListAdapter().notifyDataSetChanged();
                //websiteAdapter.notifyDataSetChanged();

                ((ImageView)refreshButtonAsImage).clearAnimation();
            }
        }.execute();
    }
}