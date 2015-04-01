package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.DateQuestionFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;

public class QuestionActivity extends Activity {
    private Survey survey;
    private int sequence = 1;
    private int maxQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle data = getIntent().getExtras();
        survey = (Survey) data.getParcelable("selected_survey");
        maxQuestions = survey.getQuestions().size();
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
        Question question = getCurrentQuestion();

        if (question.getAttachments()==null){
            System.out.println("Error! no attachments");
        } else {
            ArrayList<Attachment> attachments = question.getAttachments();
            Attachment attachment = attachments.get(0);
            Fragment fragment = null;

            //make a choice
            String type = attachment.getTYPE();
            switch (type) {
                case "video":
                    fragment = new VideoFragment();
                    break;
                case "audio":
                    break; //do something
                case "picture":
                    break; //do something
                default:
                    break; //load default image
            }
            //Add attachment to attachment fragment
            Bundle attachmentBundle = new Bundle();
            attachmentBundle.putParcelable("attachment",attachment);
            fragment.setArguments(attachmentBundle);

            //place fragment in the layout
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.question_attachment, fragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }

    //displays the question fragment
    public void displayQuestion(){
        Question question = getCurrentQuestion();
        String type = question.getType();

        Fragment fragment = null;
        switch(type){
            case "multiple_choice": break; //do something
            case "multiple_select": break; //do something
            case "open": break; //do something
            case "time": break; //do something
            case "date":
                fragment = new DateQuestionFragment(); break;
            case "datetime": break; //do something
            case "picture": break; //do something
            case "video": break; //do something
            case "audio": break; //do something
            default: break; //default
        }
        //add the question object to the fragment object through bundle
        Bundle questionBundle = new Bundle();
        questionBundle.putParcelable("question",question);
        fragment.setArguments(questionBundle);

        //place fragment in the layout
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_answer, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    public void updateView(){
        if (sequence> maxQuestions){
           finishSurvey();
        } else {
            loadProgress();
            displayAttachment();
            displayQuestion();
        }
    }

    //returns current question according to the sequence
    public Question getCurrentQuestion(){
        Question question = null;
        ArrayList<Question> questions = survey.getQuestions();
        for (Question q : questions){
            if (q.getSequence()==sequence){
                question = q;
                break;
            }
        }
        return question;
    }

    //method for the next button
    public void nextQuestion(){
        saveProgress();
        sequence++;
        updateView();
    }

    //method for the previous button
    public void previousQuestion(){
        if (maxQuestions > 1) {
            saveProgress();
            sequence--;
            updateView();
        }
    }

    public void finishSurvey(){
        //send data to server
    }

    public boolean saveProgress(){
        //save progress to SQLite local database
        return true;
    }

    public boolean loadProgress(){
        //load previously committed progress
        return true;
    }
}
