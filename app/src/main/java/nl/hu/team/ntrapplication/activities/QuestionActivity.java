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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.attachmentFragments.AudioFragment;
import nl.hu.team.ntrapplication.attachmentFragments.ImageFragment;
import nl.hu.team.ntrapplication.attachmentFragments.InfoscreenFragment;
import nl.hu.team.ntrapplication.attachmentFragments.VideoFragment;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;
import nl.hu.team.ntrapplication.objects.User;
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
    private JSONArray result;
    private Survey survey;
    private ArrayList<Question> questions;
    private int sequence = 1;
    private int maxQuestions;
    private Fragment attachmentFragment;
    private AnswerOption optionFragment;
    private DatabaseHandler db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        db = new DatabaseHandler(this);
        //Get survey object
        Bundle data = getIntent().getExtras();
        survey = (Survey) data.getParcelable("selected_survey");
        //getQuestions(survey.getId());
        //set initial attributes

        questions = db.getQuestionBySurvey(survey);
        System.out.println(survey);

        maxQuestions = survey.getQuestions().size();
        //disable the previous button
        Button b = (Button) findViewById(R.id.question_button_previous);
        b.setEnabled(false);

        //initialize JSON
        result = new JSONArray();

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
        if (question == null){
            System.out.println("Jiry wil naar huis");
        }
        Attachment attachment;
        if (question.getAttachments() == null || question.getAttachments().size() == 0) {
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
            case "infoscreen":
                optionFragment = new InfoscreenFragment();
                break;
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
        if (sequence > maxQuestions && maxQuestions > 0) {
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
        String s = createFinalJson().toString();
        Toast.makeText(this.getApplicationContext(),"bla",Toast.LENGTH_LONG).show();
        try{StringEntity entity = new StringEntity(s);
            invokeWS(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public boolean saveProgress() {
        JSONObject oneQuestion = new JSONObject();
        try {
            String valueQuestion = String.valueOf(getCurrentQuestion().getId());
            String valueAnswer = optionFragment.getValue();
            boolean exists = false;

            for(int i = 0; i < result.length();i++){
                if(valueQuestion.equals(result.getJSONObject(i).get("question"))) {
                    exists = true;
                    oneQuestion = result.getJSONObject(i);
                }
            }
            if(exists) {
                oneQuestion.put("answer",valueAnswer);
            } else {
                oneQuestion.put("question", valueQuestion);
                oneQuestion.put("answer", valueAnswer);

                result.put(oneQuestion);
            }
            System.out.println(result);

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
    public void invokeWS(StringEntity entity) {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(this.getApplicationContext(), "http://10.0.2.2:8080/NTR_application/rest/answer",
                entity, "application/json", new JsonHttpResponseHandler()
//                {
//
//                    // When the response returned by REST has Http response code '200'
//                    @Override
//                    public void onSuccess(String response) {
//                        Toast.makeText(getApplicationContext(), "Succesfully posted answers", Toast.LENGTH_LONG).show();
//                    }
//
//                    // When the response returned by REST has Http response code other than '200'
//                    @Override
//                    public void onFailure(int statusCode, Throwable error,
//                                          String content) {
//                        // When Http response code is '404'
//                        if (statusCode == 404) {
//                            //TODO foutmelding tonen
//                        }
//                        // When Http response code is '500'
//                        else if (statusCode == 500) {
//                            //TODO foutmelding tonen
//                        }
//                        // When Http response code other than 404, 500
//                        else {
//                            //TODO foutmelding tonen
//                        }
//                    }
//                }
    );
    }
    //get object with answers, userID en surveyID and return object with two id's and arrayList of answers
    public JSONObject createFinalJson(){
        JSONObject finalJson = new JSONObject();
        //Get userId from SQL lite database
        DatabaseHandler db = new DatabaseHandler(this);
        User user = db.getUser();
        //get Answer JSON object and turn it into an JSON-Array
        try {
            finalJson.put("user",user);
            finalJson.put("survey", survey);
            finalJson.put("answers",result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(finalJson.toString());
        return finalJson;
    }

//    public void getQuestions(int surveyId){
//        final Research research = db.getResearchByID(surveyId);
//
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        System.out.println("HALLO IK BEN DE SurveyService");
//
//        client.get("http://92.109.52.61:7070/NTR_application/rest/question/" + surveyId, new AsyncHttpResponseHandler() {
//
//            // When the response returned by REST has Http response code '200'
//            @Override
//            public void onSuccess(String response) {
//                // Gets an JSON object with surveys
//                ArrayList<Question> allQuestions = new ArrayList<Question>();
//                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
//                System.out.println(jsonArray.toString());
//                for (JsonElement e : jsonArray) {
//                    JsonObject object = (JsonObject) e;
//                    System.out.println("objecten " + object.toString());
//                    Question question = new Gson().fromJson(object, Question.class);
//                    allQuestions.add(question);
//                    db.addQuestion(question, survey);
//                }
//                System.out.println("WIN " + allQuestions.toString());
//            }
//
//            // When the response returned by REST has Http response code other than '200'
//            @Override
//            public void onFailure(int statusCode, Throwable error,
//                                  String content) {
//                System.out.println("QuestionFAIL");
//            }
//        });
//    }
}
