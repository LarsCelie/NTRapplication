package nl.hu.team.ntrapplication.asyncServices;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;

/**
 * Created by Tinus on 18-6-2015.
 */
public class SurveyService extends Activity {
    private DatabaseHandler db = new DatabaseHandler(this);

    public SurveyService(int researchId){
        final Research research = db.getResearchByID(researchId);


        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("HALLO IK BEN DE SurveyService");

        client.get("http://92.109.52.61:7070/NTR_application/rest/research" + researchId, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Gets an JSON object with surveys
                ArrayList<Survey> allSurveys = new ArrayList<Survey>();
                JsonArray jsonArray = new JsonParser().parse(new String(responseBody)).getAsJsonArray();
                System.out.println(jsonArray.toString());
                for(JsonElement e: jsonArray){
                    JsonObject object = (JsonObject) e;
                    System.out.println("objecten " + object.toString());
                    Survey survey = new Gson().fromJson(object,Survey.class);
                    allSurveys.add(survey);
                    db.addSurvey(survey,research);
                }
                System.out.println("WIN " +allSurveys.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("SurveyFAIL");
            }
        });
    }
}
