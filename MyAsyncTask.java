package com.example.bhagy.incrementdecrementhandlers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bhagy on 5/22/2018.
 */

public class MyAsyncTask extends AsyncTask {

    private static final String url = "https://www.google.com/";
    String stringResponse = "";
    public NetworkCHeck networkCHeck = null;

    interface NetworkCHeck {

        public void networkResponse(String response);
    }

    public MyAsyncTask(NetworkCHeck networkCHeck) {
        this.networkCHeck = networkCHeck;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL urls = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urls.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                stringResponse = "Network Detected";
            } else {
                stringResponse = "No Network" + httpURLConnection.getResponseMessage();
                Log.d("responsss", stringResponse);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        networkCHeck.networkResponse(stringResponse);
        super.onPostExecute(o);
    }
}
