package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;

/**
 * Created by Milamber on 25-3-2015.
 */
public class SurveyListActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView surveyList;
    private ArrayAdapter<Survey> adapter;

    private DatabaseHandler db = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);
        Bundle data = getIntent().getExtras();
        Research research = (Research) data.getParcelable("selected_research");

        db = new DatabaseHandler(this);
        getSurveys(research.getId());
        //get all surveys from rest server
        ArrayList<Survey> surveys = db.getSurveyByResearch(research);



        surveyList = (ListView) findViewById(R.id.listViewSurveys);
        adapter = new ArrayAdapter<Survey>(this, android.R.layout.simple_list_item_1, surveys);
        surveyList.setAdapter(adapter);
        TextView textView = (TextView)findViewById(R.id.textViewSelectedResearch);
        textView.setText(research.toString());
        surveyList.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Survey survey = (Survey)parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("selected_survey", survey);
        startActivity(intent);
    }

    public void getSurveys(int researchId){
        final Research research = db.getResearchByID(researchId);


        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("HALLO IK BEN DE SurveyService");

        client.get("http://92.109.52.61:7070/NTR_application/rest/survey/research/" + researchId, new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Gets an JSON object with surveys
                ArrayList<Survey> allSurveys = new ArrayList<Survey>();
                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
                System.out.println(jsonArray.toString());
                for (JsonElement e : jsonArray) {
                    JsonObject object = (JsonObject) e;
                    System.out.println("objecten " + object.toString());
                    Survey survey = new Gson().fromJson(object, Survey.class);
                    allSurveys.add(survey);
                    db.addSurvey(survey, research);
                }
                System.out.println("WIN " + allSurveys.toString());
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                System.out.println("SurveyFAIL");
            }
        });
    }
}
