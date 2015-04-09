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
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.optionFragments.AccelerometerFragment;


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
        s1.setName("Onderzoek 1");
        s1.setBeginDate(convertStringToDate("01-01-2011"));
        s1.setEndDate(convertStringToDate("02-02-2022"));
        r1.addSurvey(s1);


        //Q1
        Question q1 = new Question();
        q1.setId(0);
        q1.setSequence(1);
        q1.setDescription("Welke datum ben je geboren?");
        q1.setType("lightsensor");

        //Q2
        Question q2 = new Question();
        q2.setId(1);
        q2.setSequence(2);
        q2.setDescription("Wat is je naam?");
        q2.setType("audio");

        //Q3
        Question q3 = new Question();
        q3.setId(2);
        q3.setSequence(3);
        q3.setDescription("Ik ben een vraag, hihihihi");
        q3.setType("compassensor");

        //Q4
        Question q4 = new Question();
        q4.setId(3);
        q4.setSequence(4);
        q4.setDescription("Welke van deze apparatuur gebruik je?");
        q4.setType("video");


        //O1
        Option o = new Option();
        o.setCONTENT("Option: Content");
        o.setVALUE("Option: Value");
        o.setID(1);

        //O2
        Option o2 = new Option();
        o2.setVALUE("A");
        o2.setCONTENT("Computer");
        o2.setID(2);

        //O3
        Option o3 = new Option();
        o3.setVALUE("B");
        o3.setCONTENT("Smartphone");
        o3.setID(3);

        //O3
        Option o4 = new Option();
        o4.setVALUE("C");
        o4.setCONTENT("Printer");
        o4.setID(4);

        //add option to question
        q1.addOption(o);
        q2.addOption(o);
        q3.addOption(o);

        q4.addOption(o2);
        q4.addOption(o3);
        q4.addOption(o4);


        //A1
        Attachment attachment = new Attachment();
        attachment.setLOCATION("R.raw.video_test_01");
        attachment.setTYPE("video");
        attachment.setID(0);

        //A2
        Attachment attachment1 = new Attachment();
        attachment1.setLOCATION("inputlogo");
        attachment1.setTYPE("image");
        attachment1.setID(1);

        //A3
        Attachment attachment2 = new Attachment();
        attachment2.setLOCATION("inputlogo2");
        attachment2.setTYPE("image");
        attachment2.setID(2);

        //add attachment to question
        q1.addAttachment(attachment);
        q2.addAttachment(attachment1);
        q3.addAttachment(attachment2);
        q4.addAttachment(attachment1);

        //Add question to survey
        s1.addQuestion(q1);
        s1.addQuestion(q2);
        s1.addQuestion(q3);
        s1.addQuestion(q4);

        //Put data in the database
        db.addResearch(r1);
        db.addResearch(r2);
        db.addSurvey(s1,r1);
        db.addQuestion(q1, db.getSurveyByID(1));
        db.addQuestion(q2, db.getSurveyByID(1));
        db.addQuestion(q3, db.getSurveyByID(1));
        db.addQuestion(q4, db.getSurveyByID(1));
        db.addOption(o2, q4);
        db.addOption(o3, q4);
        db.addOption(o4, q4);
        db.addAttachment(attachment, q1);
        db.addAttachment(attachment1, q2);
        db.addAttachment(attachment2, q3);
        db.addAttachment(attachment1, q4);

        //Test survey with all questions

        Research tr = new Research();
        tr.setSTATUS("open");
        tr.setID(9);
        tr.setNAME("Fruitonderzoek");
        tr.setBEGIN_DATE(convertStringToDate("01-01-2015"));
        tr.setEND_DATE(convertStringToDate("10-10-2015"));
        db.addResearch(tr);

        Survey ts = new Survey();
        ts.setEndDate(convertStringToDate("10-10-2015"));
        ts.setBeginDate(convertStringToDate("01-01-2015"));
        ts.setName("Appels en peren");
        ts.setStatus("open");
        ts.setId(9);
        db.addSurvey(ts,tr);

        Question tq1 = new Question();
        tq1.setId(10);
        tq1.setType("infoscreen");
        tq1.setNaam("Appels en peren");
        tq1.setDescription("In het volgende onderzoek zullen wij appels met peren gaan vergelijken");
        tq1.setSequence(1);
        db.addQuestion(tq1,ts);

        Question tq2 = new Question();
        tq2.setId(11);
        tq2.setType("date");
        tq2.setNaam("Appel/peer gegeten");
        tq2.setDescription("Vul de datum in op welk u het laatst een appel of peer heeft gegeten");
        tq2.setSequence(2);
        db.addQuestion(tq2,ts);

        Question tq3 = new Question();
        tq3.setId(12);
        tq3.setType("time");
        tq3.setNaam("Appel/peer nuttigen");
        tq3.setDescription("Hoe laat eet u meestal een appel of peer wanneer u dit doet");
        tq3.setSequence(3);
        db.addQuestion(tq3,ts);

        Question tq4 = new Question();
        tq4.setId(13);
        tq4.setType("multiple_choice");
        tq4.setNaam("Appel of peer");
        tq4.setDescription("Eet u meer appels of peren");
        tq4.setSequence(4);
        db.addQuestion(tq4,ts);

        Option tq4o1 = new Option();
        tq4o1.setID(10);
        tq4o1.setVALUE("A");
        tq4o1.setCONTENT("Appels");
        db.addOption(tq4o1,tq4);

        Option tq4o2 = new Option();
        tq4o2.setID(11);
        tq4o2.setVALUE("B");
        tq4o2.setCONTENT("Peren");
        db.addOption(tq4o2,tq4);

        Question tq5 = new Question();
        tq5.setId(14);
        tq5.setType("multiple_select");
        tq5.setNaam("Hoeveel appels/peren");
        tq5.setDescription("Hoeveel appels en/of peren eet u per week");
        tq5.setSequence(5);
        db.addQuestion(tq5,ts);

        Option tq5o1 = new Option();
        tq5o1.setID(20);
        tq5o1.setVALUE("A");
        tq5o1.setCONTENT("Geen");
        db.addOption(tq5o1,tq5);

        Option tq5o2 = new Option();
        tq5o2.setID(21);
        tq5o2.setVALUE("B");
        tq5o2.setCONTENT("1 tot 7");
        db.addOption(tq5o2,tq5);

        Option tq5o3 = new Option();
        tq5o3.setID(22);
        tq5o3.setVALUE("C");
        tq5o3.setCONTENT("8 tot 14");
        db.addOption(tq5o3,tq5);

        Option tq5o4 = new Option();
        tq5o4.setID(23);
        tq5o4.setVALUE("D");
        tq5o4.setCONTENT("Meer dan 14");
        db.addOption(tq5o4,tq5);

        Question tq6 = new Question();
        tq6.setId(15);
        tq6.setType("open");
        tq6.setNaam("Ervaringen appels/peren");
        tq6.setDescription("Geef aan waarom u wel (of niet) van appels en/of peren houdt");
        tq6.setSequence(6);
        db.addQuestion(tq6,ts);

        Question tq7 = new Question();
        tq7.setId(30);
        tq7.setType("accelerometer");
        tq7.setNaam("Beweging");
        tq7.setDescription("Beweeg uw telefoon alsof u fruit eet.");
        tq7.setSequence(7);
        db.addQuestion(tq7, ts);

        Question tq8 = new Question();
        tq8.setId(31);
        tq8.setType("gps");
        tq8.setNaam("GPS");
        tq8.setDescription("Bepaal u locatie met GPS");
        tq8.setSequence(8);
        db.addQuestion(tq8,ts);

        Attachment ta1 = new Attachment();
        ta1.setLOCATION("appels_en_peer");
        ta1.setTYPE("image");
        ta1.setID(10);

        Attachment ta2 = new Attachment();
        ta2.setLOCATION("");
        ta2.setTYPE("audio");
        ta2.setID(11);

        db.addAttachment(ta2,tq3);
        db.addAttachment(ta1, tq2);
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
}

