package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.AudioFragment;
import nl.hu.team.ntrapplication.attachmentFragments.ImageFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.optionFragments.AccelerometerFragment;
import nl.hu.team.ntrapplication.optionFragments.AnswerOption;
import nl.hu.team.ntrapplication.optionFragments.CompasSensorFragment;
import nl.hu.team.ntrapplication.optionFragments.DateQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.GlobalPositioningSystemFragment;
import nl.hu.team.ntrapplication.optionFragments.GyroscopeFragment;
import nl.hu.team.ntrapplication.optionFragments.LightSensorFragment;
import nl.hu.team.ntrapplication.optionFragments.MulitipleChoiceFragment;
import nl.hu.team.ntrapplication.optionFragments.MultipleSelectQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.OpenQuestionFragment;
import nl.hu.team.ntrapplication.optionFragments.RecordAudioFragment;
import nl.hu.team.ntrapplication.optionFragments.RecordVideoFragment;
import nl.hu.team.ntrapplication.optionFragments.TakePhotoFragment;
import nl.hu.team.ntrapplication.optionFragments.TimeQuestionFragment;

public class QuestionActivity extends Activity {
    private JSONObject result;
    private Survey survey;
    private int sequence = 1;
    private int maxQuestions;
    private Fragment attachmentFragment;
    private AnswerOption optionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Get survey object
        Bundle data = getIntent().getExtras();
        survey = data.getParcelable("selected_survey");

        //set initial attributes
        maxQuestions = survey.getQuestions().size();

        //disable the previous button
        Button b = (Button) findViewById(R.id.question_button_previous);
        b.setEnabled(false);

        //initialize JSON
        result = new JSONObject();

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
                optionFragment = new RecordAudioFragment();
                break;
//            case "infoscreen":
//                optionFragment = new InfoscreenFragment();
//                break;
            case "accelerometer":
                optionFragment = new AccelerometerFragment();
                break;
            case "gps":
                optionFragment = new GlobalPositioningSystemFragment();
                break;
            case "lightsensor":
                optionFragment = new LightSensorFragment();
                break;
            case "compassensor":
                optionFragment = new CompasSensorFragment();
                break;
            case "gyroscope":
                optionFragment = new GyroscopeFragment();
                break;
            default:
                break; //TODO: add default
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
//            optionFragment.onPause();
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
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    saveProgress();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };
    public void pauseSurvey(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pause);
        builder.setMessage(R.string.pause_message);
        builder.setPositiveButton(getString(R.string.yes), dialogClickListener);
        builder.setNegativeButton(getString(R.string.no), dialogClickListener);
        builder.show();
    }

    @Override
    public void onPause() {
        super.onPause();

    }
    @Override
    public void onResume() {
        super.onResume();
    }

    public void finishSurvey() {
        //Intent intent = new Intent(this, SplashScreenActivity.class);
        //startActivity(intent);
        //TODO: service aanroepen de JSON-Object met antwoorden verstuurd naar REST-server
        //TODO: Finish the survey and continue to next screen
    }

    public boolean saveProgress() {
        try {
            String key = String.valueOf(getCurrentQuestion().getId());
            String value = optionFragment.getValue();
            result.put(key, value);
            return true;
        } catch (JSONException e){
            return false;
        }
        //TODO: save progress to SQLite local database
    }

    public boolean loadProgress() {
        //TODO: load previously committed progress from SQLite local database
        return false;
    }
}
