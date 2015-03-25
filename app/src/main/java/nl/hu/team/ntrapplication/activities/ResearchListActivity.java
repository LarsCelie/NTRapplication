package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;

public class ResearchListActivity extends Activity {
    ListView researchList;
    ArrayAdapter<Research> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_list);

        DatabaseHandler db = new DatabaseHandler(this);

        // TEST DATA
        Research r1 = new Research();
        r1.setID(1);
        r1.setNAME("Onderzoek 1");
        r1.setBEGIN_DATE(convertStringToDate("01-03-2015"));
        r1.setEND_DATE(convertStringToDate("29-03-2015"));
        r1.setSTATUS("Iets");
        db.addResearch(r1);

        List<Research> researches = db.getAllResearch();

        researchList = (ListView) findViewById(R.id.awesomeListView);
        adapter = new ArrayAdapter<Research>(this, android.R.layout.simple_list_item_1, researches);
        researchList.setAdapter(adapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_research_list, menu);
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
