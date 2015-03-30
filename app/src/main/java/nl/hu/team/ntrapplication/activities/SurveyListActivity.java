package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;

/**
 * Created by Milamber on 25-3-2015.
 */
public class SurveyListActivity extends Activity {
    ListView surveyList;
    ArrayAdapter<Survey> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);
        Bundle data = getIntent().getExtras();
        Research research = (Research) data.getParcelable("selected_research");

        surveyList = (ListView) findViewById(R.id.listViewSurveys);
        adapter = new ArrayAdapter<Survey>(this, android.R.layout.simple_list_item_1, research.getSurveys());
        surveyList.setAdapter(adapter);
        TextView textView = (TextView)findViewById(R.id.textViewSelectedResearch);
        textView.setText(research.getNAME());
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
}
