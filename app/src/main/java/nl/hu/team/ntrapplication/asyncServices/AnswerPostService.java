package nl.hu.team.ntrapplication.asyncServices;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.InputStream;

/**
 * Created by Lars on 6/23/2015.
 */
public class AnswerPostService {


    AsyncHttpClient client = new AsyncHttpClient();

    public void postMedia(InputStream fileStream, String fileName){
        RequestParams params = new RequestParams();
        params.put("media", fileStream, fileName);
        client.post("http://10.0.2.2:8080/NTR_application/rest/media",params, new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {

            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error, String content) {

            }

        });
    }
}

