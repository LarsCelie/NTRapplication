package nl.hu.team.ntrapplication.asyncServices;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by Lars on 6/23/2015.
 */
public class AnswerPostService extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/NTR_application/rest/answer/test");
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            entityBuilder.addTextBody("userid","Testing!");
            //TODO: map the rest


            HttpEntity entity = entityBuilder.build();
            httpPost.setEntity(entity);
            httpClient.execute(httpPost);

        } catch(IOException e){

        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

