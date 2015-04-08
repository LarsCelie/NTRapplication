package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.AudioFragment;
import nl.hu.team.ntrapplication.attachmentFragments.ImageFragment;
import nl.hu.team.ntrapplication.attachmentFragments.InfoscreenFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.optionFragments.DateQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.MulitipleChoiceFragment;
import nl.hu.team.ntrapplication.optionFragments.MultipleSelectQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.OpenQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.RecordVideoFragment;
import nl.hu.team.ntrapplication.optionFragments.TakePhotoFragment;
import nl.hu.team.ntrapplication.optionFragments.TimeQuestionFragment;

public class QuestionActivity extends Activity {
    private Survey survey;
    private int sequence = 1;
    private int maxQuestions;
    private Fragment attachmentFragment;
    private Fragment optionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Get survey object
        Bundle data = getIntent().getExtras();
        survey = (Survey) data.getParcelable("selected_survey");

        //set initial attributes
        maxQuestions = survey.getQuestions().size();

        //disable the previous button
        Button b = (Button) findViewById(R.id.question_button_previous);
        b.setEnabled(false);

        //call update
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

    public void displayAttachment() {
        Question question = getCurrentQuestion();
        Attachment attachment;
        if (question.getAttachments().size() == 0) {
            System.out.println("Error! no attachments");
            attachment = new Attachment();
            attachment.setTYPE("image");
            attachment.setLOCATION("inputlogo");
        } else {
            ArrayList<Attachment> attachments = question.getAttachments();
            attachment = attachments.get(0);
        }

        //make a choice
        String type = attachment.getTYPE();
        switch (type) {
            case "video":
                attachmentFragment = new VideoFragment();
                break;
            case "audio":
                attachmentFragment = new AudioFragment();
                break;
            case "image":
                attachmentFragment = new ImageFragment();
                break;
            default:
                attachmentFragment = new ImageFragment();
                //load the default image
                break;
        }
        //Add attachment to attachment fragment
        Bundle attachmentBundle = new Bundle();
        attachmentBundle.putParcelable("attachment", attachment);
        attachmentFragment.setArguments(attachmentBundle);

        //place fragment in the layout
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_attachment, attachmentFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    //displays the question fragment
    public void displayQuestion() {
        Question question = getCurrentQuestion();
        String type = question.getType();

        switch (type) {
            case "multiple_choice":
                optionFragment = new MulitipleChoiceFragment();
                break;
            case "multiple_select":
                optionFragment = new MultipleSelectQuestionFragment();
                break;
            case "open":
                optionFragment = new OpenQuestionFragment();
                break;
            case "time":
                optionFragment = new TimeQuestionFragment();
                break;
            case "date":
                optionFragment = new DateQuestionFragment();
                break;
            case "datetime":
                break; //TODO: datetime answer
            case "picture":
                optionFragment = new TakePhotoFragment();
                break;
            case "video":
                optionFragment = new RecordVideoFragment();
                break;
            case "audio":
                break; //TODO: audio answer
            case "infoscreen":
                optionFragment = new InfoscreenFragment();
                break;
            default:
                break; ////TODO: add default
        }
        //add the question object to the fragment object through bundle
        Bundle questionBundle = new Bundle();
        questionBundle.putParcelable("question", question);
        optionFragment.setArguments(questionBundle);

        //place fragment in the layout
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_answer, optionFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    public void updateView() {
        if (sequence > maxQuestions) {
            finishSurvey();
        } else {
            loadProgress();
            displayAttachment();
            displayQuestion();
        }
    }

    //returns current question according to the sequence
    public Question getCurrentQuestion() {
        Question question = null;
        ArrayList<Question> questions = survey.getQuestions();
        for (Question q : questions) {
            if (q.getSequence() == sequence) {
                question = q;
                break;
            }
        }
        return question;
    }

    //method for the next button
    public void nextQuestion(View view) {
        Button button = (Button) findViewById(R.id.question_button_previous);
        if (!button.isEnabled()){
            button.setEnabled(true);
        }
        saveProgress();
        sequence++;
        updateView();
    }

    //method for the previous button
    public void previousQuestion(View view) {
        if (sequence > 1) {
            saveProgress();
            sequence--;
            if (sequence <=1){
                Button button = (Button) findViewById(R.id.question_button_previous);
                button.setEnabled(false);
            }
            updateView();
        }
    }

    public void finishSurvey() {
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
        //TODO: Finish the survey and continue to next screen
    }

    public boolean saveProgress() {
        //TODO: save progress to SQLite local database
//        optionFragment.getAnswerValue();
        return true;
    }

    public boolean loadProgress() {
        //TODO: load previously committed progress from SQLite local database
        return false;
    }
}
