package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Research;

public class ResearchListActivity extends Activity implements OnItemClickListener {
    ListView researchList;
    ArrayAdapter<Research> adapter;
    private MyCustomAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_list);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Research> researches = db.getAllResearch();

        dataAdapter = new MyCustomAdapter(this,R.layout.layout_tiles,researches);


        researchList = (ListView) findViewById(R.id.awesomeListView);
        researchList.setAdapter(dataAdapter);
        researchList.setOnItemClickListener(this);
    }
    private class MyCustomAdapter extends ArrayAdapter<Research> {
        private ArrayList<Research> researches;
        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Research> researches) {
            super(context,textViewResourceId,researches);
            this.researches = new ArrayList<>();
            this.researches = researches;
        }

        //Contains the elements of layout_research_element
        private class ViewHolder {
            TextView title;
            ImageView background;
            TextView days_left;
            TextView more;
        }

        @Override
        public View getView(int position,View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if(convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.layout_tiles, null);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.tiles_title);
                holder.background = (ImageView) convertView.findViewById(R.id.tiles_image);
                holder.days_left = (TextView) convertView.findViewById(R.id.tiles_secondary_info);
                holder.more = (TextView) convertView.findViewById(R.id.tiles_more_info);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Research research = researches.get(position);
            holder.title.setText(" "+research.toString());
            int resID = getResources().getIdentifier("test_research_background", "drawable", getPackageName());
            holder.background.setImageResource(resID);
            holder.title.setTag(research);
            holder.more.setText(R.string.research_more);
            return convertView;
        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Research research = (Research)parent.getAdapter().getItem(position);

        Intent intent = new Intent(this, SurveyListActivity.class);
        intent.putExtra("selected_research", research);
        startActivity(intent);
    }
}
