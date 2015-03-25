package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import nl.hu.team.ntrapplication.R;

/**
 * Created by Milamber on 25-3-2015.
 */
public class SurveyListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
