package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;

public class ResearchListActivity extends Activity implements OnItemClickListener {
    ListView researchList;
    ArrayAdapter<Research> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_list);

        DatabaseHandler db = new DatabaseHandler(this);

        //Test data moved to main activity

        List<Research> researches = db.getAllResearch();

        researchList = (ListView) findViewById(R.id.awesomeListView);
        adapter = new ArrayAdapter<Research>(this, android.R.layout.simple_list_item_1, researches);
        researchList.setAdapter(adapter);
        researchList.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Research research = (Research)parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, SurveyListActivity.class);
        intent.putExtra("selected_research", research);
        startActivity(intent);
    }
}
