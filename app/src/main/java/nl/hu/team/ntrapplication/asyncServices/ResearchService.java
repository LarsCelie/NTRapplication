package nl.hu.team.ntrapplication.asyncServices;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;

/**
 * Created by Tinus on 18-6-2015.
 */
public class ResearchService extends Activity {
    private DatabaseHandler db = new DatabaseHandler(this);
    // Method that performs RESTful webservice invocations
    public ResearchService() {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("HALLO IK BEN DE ResearchService");

        client.get("http://92.109.48.222:7070/NTR_application/rest/research", new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                 // Gets an JSON object with researsches
                // Write user Data to SQLite
                ArrayList<Research> allResearches = new ArrayList<Research>();
                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
                System.out.println(jsonArray.toString());
                for(JsonElement e: jsonArray){
                    JsonObject object = (JsonObject) e;
                    System.out.println("objecten " + object.toString());
                    Research research = new Gson().fromJson(object,Research.class);
                    allResearches.add(research);
                    db.addResearch(research);
                }
                System.out.println("WIN " +allResearches.toString());
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Toast.makeText(getApplicationContext(), "ERROR!" + content + error + statusCode, Toast.LENGTH_LONG).show();
                System.out.println("FAIL");
            }
        });
    }
}
