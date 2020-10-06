package com.example.articlefeed.WebServices;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class checker extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.hayadan.org.il/%D7%9E%D7%92%D7%9C%D7%99-%D7%A0%D7%92%D7%99%D7%A3-%D7%A6%D7%94%D7%91%D7%AA-c-%D7%96%D7%9B%D7%95-%D7%91%D7%A4%D7%A8%D7%A1-%D7%A0%D7%95%D7%91%D7%9C-%D7%9C%D7%A8%D7%A4%D7%95%D7%90%D7%94-%D7%A9%D7%99";

        try {
            Document webpage = Jsoup.connect(url).get();
            Log.d("finsished","????");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Void wtf = null;
        return wtf;
    }
}
