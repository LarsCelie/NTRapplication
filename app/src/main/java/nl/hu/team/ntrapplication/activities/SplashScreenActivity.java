package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.asyncServices.ResearchService;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.User;

/**
 * Created by jiry on 31-3-2015.
 */
public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    DatabaseHandler db = new DatabaseHandler(this);
    User u = db.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(u != null) {

            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    String username = u.getUsername();
                    String password = u.getPassword();

                    // Instantiate Http Request Param Object
                    RequestParams params = new RequestParams();

                    // Http parameters
                    params.put("username", username);
                    params.put("password", password);
                    invokeWS(params);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    ResearchService researchService = new ResearchService();
                    Intent i = new Intent(SplashScreenActivity.this, InlogActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

    // Method that performs RESTful webservice invocations
    public void invokeWS(RequestParams params) {

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        client.post("http://62.45.47.22:8080/NTR_application/rest/session", params, new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {

                // Navigate to Home screen
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                Toast.makeText(getApplicationContext(), "ERROR!" + content + error + statusCode, Toast.LENGTH_LONG).show();
            }
        });


    }
}
