package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;

public class QuestionActivity extends Activity {
    private ArrayList<Question> questions;
    private int sequence = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle data = getIntent().getExtras();
        Survey survey = (Survey) data.getParcelable("selected_survey");
        questions = survey.getQuestions();
        updateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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

    public void displayAttachment(){

    }

    public void displayOptions(){

    }

    public void displayQuestion(){

    }

    public void updateView(){
        displayAttachment();
        displayOptions();
        displayQuestion();
    }
}
