package com.example.articlefeed.WebServices.DownloadManager;

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

import java.util.ArrayList;

public class DownloadManager {
    private boolean isLastItem;
    public void Hayadaan(ArrayList<ArticleItem> hayadaanArticles, WebsiteItem hayadaanWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new  HayadaanDownloader(hayadaanArticles)  {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);

                if (hayadaanWebsiteItem.getArticleListAdapter() != null)
                    hayadaanWebsiteItem.getArticleListAdapter().notifyDataSetChanged();

                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

                //pd.dismiss();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.hayadan.org.il/feed/");
    }

    public void HayadaanImages(ArrayList<ArticleItem> hayadaanArticles, WebsiteItem hayadaanWebsiteItem)
    {
        new HayadaanImageDownloader(hayadaanArticles){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                hayadaanWebsiteItem.getArticleListAdapter().notifyDataSetChanged();


                //websiteAdapter.notifyDataSetChanged();

                //((ImageView) MainActivity.refreshButtonAsImage).clearAnimation(); // last job at refresh.
                //MainActivity.refreshButtonAsImage.setEnabled(true);
            }
        }.execute();
    }

    public void Davidson(ArrayList<ArticleItem> davidsonArticles, WebsiteItem davidsonWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new  DavidsonDownloader(davidsonArticles)  {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);
                if (davidsonWebsiteItem.getArticleListAdapter() != null)
                    davidsonWebsiteItem.getArticleListAdapter().notifyDataSetChanged();

                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

                //pd.dismiss();
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://davidson.weizmann.ac.il/");
    }

    public void The7Eye(ArrayList<ArticleItem> the7EyeArticles, WebsiteItem the7EyeWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new  The7EyeDownloader(the7EyeArticles) {
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleList) {
                super.onPostExecute(articleList);

                if (the7EyeWebsiteItem.getArticleListAdapter() != null)
                    the7EyeWebsiteItem.getArticleListAdapter().notifyDataSetChanged();

                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.the7eye.org.il/");
    }

    public void Haaretz(ArrayList<ArticleItem> haaretzArticles, WebsiteItem haaretzWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new HaaretzDownloader(haaretzArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (haaretzWebsiteItem.getArticleListAdapter() != null)
                    haaretzWebsiteItem.getArticleListAdapter().notifyDataSetChanged();

                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }
                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.haaretz.co.il/cmlink/1.1470869");
    }

    public void HaMakom(ArrayList<ArticleItem> hamakomArticles, WebsiteItem hamakomWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new HaMakomDownloader(hamakomArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (hamakomWebsiteItem.getArticleListAdapter() != null)
                    hamakomWebsiteItem.getArticleListAdapter().notifyDataSetChanged();

                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.ha-makom.co.il/feed/");
    }

    public void Mekomit(ArrayList<ArticleItem> mekomitArticles, WebsiteItem mekomitWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new MekomitDownloader(mekomitArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (mekomitWebsiteItem.getArticleListAdapter() != null)
                    mekomitWebsiteItem.getArticleListAdapter().notifyDataSetChanged();


                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

                //websiteAdapter.notifyDataSetChanged();
            }
        }.execute("https://www.mekomit.co.il/feed/");
    }

    public void FriendsOfGeorge(ArrayList<ArticleItem> friendsOfGeorgeArticles, WebsiteItem friendsOfGeorgeWebsiteItem,boolean isLastItem, OnDataDownloadComplete handler)
    {
        new FriendsOfGeorgeDownloader(friendsOfGeorgeArticles){
            @Override
            protected void onPostExecute(ArrayList<ArticleItem> articleItems) {
                super.onPostExecute(articleItems);

                if (friendsOfGeorgeWebsiteItem.getArticleListAdapter() != null)
                    friendsOfGeorgeWebsiteItem.getArticleListAdapter().notifyDataSetChanged();


                if (isLastItem == true) {
                    handler.DataDownloadCompleted();
                }

            }
        }.execute("https://www.hahem.co.il/friendsofgeorge/");
    }

    public void downloadSelectedData(ArrayList<WebsiteItem> selectedWebsites, OnDataDownloadComplete handler)
    {
        isLastItem = false;
        for (int i = 0; i < selectedWebsites.size(); i++) {

            if ( i == selectedWebsites.size() - 1) { // last item - signal to activate the handler.
                selectDownloaderFromName(selectedWebsites.get(i),handler,true);
            }
            else{

            selectDownloaderFromName(selectedWebsites.get(i),handler,false);
            }
        }

    }

    public void selectDownloaderFromName(WebsiteItem item, OnDataDownloadComplete handler, Boolean isLastItem)
    {
        switch(item.getWebsiteName())
        {
            case "הידען":
                Hayadaan(item.getArticleList(),item,isLastItem,handler);
                break;

            case "מכון דוידסון":
                Davidson(item.getArticleList(),item,isLastItem,handler);
                break;

            case "העין השביעית":
                The7Eye(item.getArticleList(),item,isLastItem,handler);
                break;

            case "הארץ":
                Haaretz(item.getArticleList(),item,isLastItem,handler);
                break;

            case "המקום הכי חם בגיהנום":
                HaMakom(item.getArticleList(),item,isLastItem,handler);
                break;

            case "שיחה מקומית":
                Mekomit(item.getArticleList(),item,isLastItem,handler);
                break;

            case "החברים של ג'ורג'":
                FriendsOfGeorge(item.getArticleList(),item,isLastItem,handler);
                break;

        }


    }


}
