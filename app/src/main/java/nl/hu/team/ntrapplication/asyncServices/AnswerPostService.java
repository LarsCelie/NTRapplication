package nl.hu.team.ntrapplication.asyncServices;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

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

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}

