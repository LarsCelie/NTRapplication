package nl.hu.team.ntrapplication.asyncServices;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageFetcher extends AsyncTask<URL,String,String> {

    protected String doInBackground(URL... url) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url[0].openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(is);
            /*
            httpPutRequest
             */
            String result = br.readLine();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}