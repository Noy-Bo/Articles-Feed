package com.example.articlefeed.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.articlefeed.Adapters.WebsiteListAdapter;
import com.example.articlefeed.Entities.ArticleItem;
import com.example.articlefeed.Entities.WebsiteItem;
import com.example.articlefeed.R;
import com.example.articlefeed.WebServices.DownloadManager.DownloadManager;
import com.example.articlefeed.WebServices.DownloadManager.OnDataDownloadComplete;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView websiteRecyclerView;
    private RecyclerView.Adapter websiteAdapter;
    private RecyclerView.LayoutManager websiteLayoutManager;

    DownloadManager downloadManager = new DownloadManager();
    private  ArrayList<WebsiteItem> websiteList;
    private  ArrayList<WebsiteItem> selectedWebsiteList;
    private ArrayList<ArticleItem> mekomitArticles;
    private ArrayList<ArticleItem> hamakomArticles;
    private ArrayList<ArticleItem> haaretzArticles;
    private ArrayList<ArticleItem> the7EyeArticles;
    private ArrayList<ArticleItem> davidsonArticles;
    private ArrayList<ArticleItem> hayadaanArticles;
    private ArrayList<ArticleItem> friendsOfGeorgeArticles;

    private WebsiteItem hayadaanWebsiteItem;
    private WebsiteItem davidsonWebsiteItem;
    private WebsiteItem the7EyeWebsiteItem;
    private WebsiteItem haaretzWebsiteItem;
    private WebsiteItem hamakomWebsiteItem;
    private WebsiteItem mekomitWebsiteItem;
    private WebsiteItem friendsOfGeorgeWebsiteItem;

    private Toolbar mainToolbar;

    public static SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // ====================================================================
    // ------------------------- Initializing arrays  ----------------------
    // ====================================================================

    initializeArrays(); // has to be first

    // ====================================================================
    // ------------------------ Toolbar Add Menu --------------------------
    // ====================================================================

        mainToolbar = (Toolbar) findViewById(R.id.menu_inside_toolbar);
        mainToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean selected = false;
                item.collapseActionView();
                for (WebsiteItem webItem : selectedWebsiteList) {
                    if (!item.toString().equals("parent_menu")) {
                        if (webItem.getWebsiteName().equals(item.toString())) {
                            Toast.makeText(MainActivity.this, "האתר שבחרת כבר נמצא ברשימה", Toast.LENGTH_SHORT).show();
                            selected = true;
                        }
                    }
                }
                if ((selected == false)&&(!item.toString().equals("parent_menu"))) {
                    for (WebsiteItem webItem : websiteList)
                    {
                        if (webItem.getWebsiteName().equals(item.toString())){
                            selectedWebsiteList.add(0,webItem);
                            downloadManager.selectDownloaderFromName(webItem, new OnDataDownloadComplete() {
                                @Override
                                public void DataDownloadCompleted() {
                                    requestImages();
                                }
                            },false);
                            websiteAdapter.notifyDataSetChanged();
                        }
                    }
                }
                return false;
            }
        });
        mainToolbar.inflateMenu(R.menu.add_menu);



        // ====================================================================
        // ------------------------- Website Adapters -------------------------
        // ====================================================================


        websiteRecyclerView = findViewById(R.id.website_recycler_view);
        websiteRecyclerView.addItemDecoration(new DividerItemDecoration(this,0));
        websiteRecyclerView.setHasFixedSize(true);
        websiteLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        websiteAdapter = new WebsiteListAdapter(selectedWebsiteList,this);

        websiteRecyclerView.setLayoutManager(websiteLayoutManager);
        websiteRecyclerView.setAdapter(websiteAdapter);


        // ====================================================================
        // ---------------------------- Alert Dialog  -------------------------
        // ====================================================================

       CreateAndDisplayInitialDialogAlert();


        // ====================================================================
        // -------------------------- Swipe To Refresh -------------------------
        // ====================================================================

        // SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light,
        android.R.color.darker_gray);

    }


    public void initializeArrays()
    {
        mekomitArticles = new ArrayList<>();
        hamakomArticles = new ArrayList<>();
        haaretzArticles = new ArrayList<>();
        the7EyeArticles = new ArrayList<>();
        davidsonArticles = new ArrayList<>();
        hayadaanArticles = new ArrayList<>();
        friendsOfGeorgeArticles = new ArrayList<>();

        websiteList = new ArrayList<>();
        selectedWebsiteList = new ArrayList<>();

        hayadaanWebsiteItem = new WebsiteItem("הידען",hayadaanArticles);
        websiteList.add(hayadaanWebsiteItem);

        davidsonWebsiteItem = new WebsiteItem("מכון דוידסון",davidsonArticles);
        websiteList.add(davidsonWebsiteItem);

        the7EyeWebsiteItem = new WebsiteItem("העין השביעית",the7EyeArticles);
        websiteList.add(the7EyeWebsiteItem);

        haaretzWebsiteItem = new WebsiteItem("הארץ",haaretzArticles);
        websiteList.add(haaretzWebsiteItem);

        hamakomWebsiteItem = new WebsiteItem("המקום הכי חם בגיהנום",hamakomArticles);
        websiteList.add(hamakomWebsiteItem);

        mekomitWebsiteItem = new WebsiteItem("שיחה מקומית",mekomitArticles);
        websiteList.add(mekomitWebsiteItem);

        friendsOfGeorgeWebsiteItem = new WebsiteItem("החברים של ג'ורג'", friendsOfGeorgeArticles);
        websiteList.add(friendsOfGeorgeWebsiteItem);
    }

    public void CreateAndDisplayInitialDialogAlert()
    {
        String[] stringWebsiteList = {"הידען","מכון דוידסון","העין השביעית","הארץ","המקום הכי חם בגיהנום","שיחה מקומית","החברים של ג'ורג'"};

        boolean[] checkedItems;
        ArrayList<Integer> itemsIntegers = new ArrayList<>();
        checkedItems = new boolean[websiteList.size()];



        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("מאיזה אתרים להציג לך כתבות?");
        mBuilder.setMultiChoiceItems(stringWebsiteList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked) {
                    if (!itemsIntegers.contains(position)) {
                        itemsIntegers.add(position);
                    }
                } else {
                    for (int i = 0; i < itemsIntegers.size(); i++)
                        if (itemsIntegers.get(i) == position) {
                            //
                            itemsIntegers.remove(i);
                            break;
                        }
                }

            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("בחרתי", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < itemsIntegers.size(); i++) {
                    selectedWebsiteList.add(websiteList.get(itemsIntegers.get(i)));

                }
                websiteAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });

        // default selected all
        AlertDialog mDialog = mBuilder.create();
        for(int i=0; i< checkedItems.length; i++) {
            itemsIntegers.add(i);
            checkedItems[i] = true;
            mDialog.getListView().setItemChecked(i, true);
        }

        mDialog.show();

    }

    @Override
    public void onRefresh() {
        downloadManager.downloadSelectedData(selectedWebsiteList, new OnDataDownloadComplete() {
            @Override
            public void DataDownloadCompleted() {
                requestImages();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    public void requestImages()
    {
        if (selectedWebsiteList.contains(hayadaanWebsiteItem))
            downloadManager.HayadaanImages(hayadaanArticles,hayadaanWebsiteItem);
    }

}