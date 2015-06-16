package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.objects.User;
import nl.hu.team.ntrapplication.optionFragments.AccelerometerFragment;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        invokeWS();
        Intent intent = new Intent(this, ResearchListActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    public void showResearchList(View view) {
        Intent intent = new Intent(this, ResearchListActivity.class);
        startActivity(intent);
    }

    public void openFragment(View view){
        Fragment newFragment = new AccelerometerFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, newFragment);
        transaction.commit();


    }

    public void showQuestion(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    private Date convertStringToDate(String input) {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date date = null;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /*
        * Hier komt iets dat de researches ophaalt er wat nuttigs van maakt
        * Waarschijnlijk moet dit een aparte Class worden
        * */


    // Method that performs RESTful webservice invocations
    public void invokeWS() {

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("HALLO IK BEN DE InvokeWS");

        client.get("http://92.109.48.222:7070/NTR_application/rest/research", new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                //Toast.makeText(getApplicationContext(), "Hoi, alle researches zijn opgehaald" + response, Toast.LENGTH_LONG).show();

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



        /*
        * Hier eindigt het deel over de researches
        * */
    // DatabaseHandler db = new DatabaseHandler(this);
}

