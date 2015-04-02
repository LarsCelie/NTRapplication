package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.ImageFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.optionFragments.DateQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.MulitipleChoiceFragment;
import nl.hu.team.ntrapplication.optionFragments.MultipleSelectQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.OpenQuestionFragment;

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

    public void displayAttachment() {
        Question question = getCurrentQuestion();
        Attachment attachment = null;
        if (question.getAttachments() == null || question.getAttachments().size() == 0) {
            System.out.println("Error! no attachments");
            attachment = new Attachment();
            attachment.setTYPE("image");
            attachment.setLOCATION("R.drawable.inputlogo");
        }
        ArrayList<Attachment> attachments = question.getAttachments();
        attachment = attachments.get(0);
        Fragment fragment = null;

        //make a choice
        String type = attachment.getTYPE();
        switch (type) {
            case "video":
                fragment = new VideoFragment();
                break;
            case "audio":
                break; //TODO: add class
            case "image":
                fragment = new ImageFragment();
                break;
            default:
                fragment = new ImageFragment();
                //load the default image
                break;
        }
        //Add attachment to attachment fragment
        Bundle attachmentBundle = new Bundle();
        attachmentBundle.putParcelable("attachment", attachment);
        fragment.setArguments(attachmentBundle);

        //place fragment in the layout
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_attachment, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    //displays the question fragment
    public void displayQuestion() {
        Question question = getCurrentQuestion();
        String type = question.getType();

        Fragment fragment = null;
        switch (type) {
            case "multiple_choice":
                fragment = new MulitipleChoiceFragment();
                break;
            case "multiple_select":
                fragment = new MultipleSelectQuestionFragment();
                break;
            case "open":
                fragment = new OpenQuestionFragment();
                break;
            case "time":
                break; //TODO: time answer
            case "date":
                fragment = new DateQuestionFragment();
                break;
            case "datetime":
                break; //TODO: datetime answer
            case "picture":
                break; //TODO: picture answer
            case "video":
                break; //TODO: video answer
            case "audio":
                break; //TODO: audio answer
            default:
                break; ////TODO: add default
        }
        //add the question object to the fragment object through bundle
        Bundle questionBundle = new Bundle();
        questionBundle.putParcelable("question", question);
        fragment.setArguments(questionBundle);

        //place fragment in the layout
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.question_answer, fragment);
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
        System.out.println("DEBUG: Number of questions in this survey: " + questions.size());
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
        saveProgress();
        sequence++;
        updateView();
    }

    //method for the previous button
    public void previousQuestion(View view) {
        if (maxQuestions > 1) {
            saveProgress();
            sequence--;
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
        return true;
    }

    public boolean loadProgress() {
        //TODO: load previously committed progress
        return true;
    }
}
