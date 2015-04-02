package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.AudioFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        // TEST DATA

        //R1
        Research r1 = new Research();
        r1.setID(1);
        r1.setNAME("Onderzoek 1");
        r1.setBEGIN_DATE(convertStringToDate("01-03-2015"));
        r1.setEND_DATE(convertStringToDate("29-03-2015"));
        r1.setSTATUS("Iets");

        //R2
        Research r2 = new Research();
        r2.setID(2);
        r2.setNAME("Onderzoek 2");
        r2.setBEGIN_DATE(convertStringToDate("01-03-2015"));
        r2.setEND_DATE(convertStringToDate("29-03-2015"));
        r2.setSTATUS("Iets");

        //S1
        Survey s1 = new Survey();
        s1.setId(1);
        s1.setStatus("InProgress");
        s1.setName("Survey 1");
        s1.setBeginDate(convertStringToDate("01-01-2011"));
        s1.setEndDate(convertStringToDate("02-02-2022"));
        r1.addSurvey(s1);


        //Q1
        Question q1 = new Question();
        q1.setSequence(1);
        q1.setDescription("Welke datum ben je geboren?");
        q1.setType("open");

        //O1
        Option o = new Option();
        o.setCONTENT("Option: Content");
        o.setVALUE("Option: Value");
        o.setID(1);

        //add option to question
        q1.addOption(o);

        //A1
        Attachment attachment = new Attachment();
        attachment.setLOCATION("R.raw.video_test_01");
        attachment.setTYPE("video");
        attachment.setID(1);

        //add attachment to question
        q1.addAttachment(attachment);

        //Add question to survey
        s1.addQuestion(q1);

        //Put data in the database
        db.addResearch(r1);
        db.addResearch(r2);
        db.addSurvey(s1,r1);
        db.addQuestion(q1, db.getSurveyByID(1));
        db.addAttachment(attachment, q1);
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
        Fragment newFragment = new AudioFragment();
       //De volgende regel is waarschijnlijk fout.
        //((VideoFragment)newFragment).playVideo();
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
}

