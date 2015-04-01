package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;

public class QuestionActivity extends Activity {
    private Survey survey;
    private int sequence = 1;
    private int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle data = getIntent().getExtras();
        survey = (Survey) data.getParcelable("selected_survey");
        max = survey.getQuestions().size()+1;
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
            switch (attachment.getTYPE()) {
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
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.question_attachment, fragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }

    public void displayQuestion(){

    }

    public void updateView(){
        if (sequence==max){
            //go to next view
        } else {
            displayAttachment();
            displayQuestion();
        }
    }

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
        sequence++;
        updateView();
    }

    //method for the previous button
    public void previousQuestion(){
        if (max > 1) {
            sequence--;
            updateView();
        }
    }
}
