package nl.hu.team.ntrapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.User;
import nl.hu.team.ntrapplication.services.Utility;

public class RegisterActivity extends Activity {

    private EditText voornaam, achternaam, email, username, password, passwordcheck;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        voornaam = (EditText) findViewById(R.id.voornaamEditText);
        achternaam = (EditText) findViewById(R.id.achternaamEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        username = (EditText) findViewById(R.id.gebruikersnaamEditText);
        password = (EditText) findViewById(R.id.wachtwoordEditText);
        passwordcheck = (EditText) findViewById(R.id.controleWachtwoordEditText);

        register = (Button) findViewById(R.id.registerButton);
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

    // Method gets triggered when the register button is clicked
    public void registerUser(View view) {

        // Get all values
        String Vfirstname = voornaam.getText().toString();
        String Vlastname = achternaam.getText().toString();
        String Vemail = email.getText().toString();
        String Vusername = username.getText().toString();
        String Vpassword = password.getText().toString();
        String Vpasswordcheck = passwordcheck.getText().toString();


        // Check on null input
        if(Utility.isNotNull(Vfirstname) && Utility.isNotNull(Vlastname) &&
                Utility.isNotNull(Vemail) && Utility.isNotNull(Vusername) &&
                Utility.isNotNull(Vpassword) && Utility.isNotNull(Vpasswordcheck)) {

            // Check if email is an valid email
            if(Utility.validate(Vemail)) {

                // Password control
                if(Vpassword.equals(Vpasswordcheck)) {

                    // Http parameters
                    User u = new User();
                    u.setFirstname(Vfirstname);
                    u.setLastname(Vlastname);
                    u.setEmail(Vemail);
                    u.setUsername(Vusername);
                    u.setPassword(Vpassword);
                    try {
                        String s = new Gson().toJson(u);
                        StringEntity entity = new StringEntity(s);
                        invokeWS(entity);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Wachtwoorden komen niet overeen", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Vul een geldig email adres in", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Vul alle velden in", Toast.LENGTH_LONG).show();
        }

    }


    // Method that performs RESTful webservice invocations
    public void invokeWS(StringEntity entity) {

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(this.getApplicationContext(),"http://92.109.48.222:7070/NTR_application/rest/session/create",
                entity,"application/json", new JsonHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                // Navigate to Home screen
                navigatetoLoginActivity();
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {

                Toast.makeText(getApplicationContext(), "ERROR!" + content + error + statusCode, Toast.LENGTH_LONG).show();
            }
        });


    }


    // Method which navigates from Register Activity to Login Activity
    public void navigatetoLoginActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),InlogActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }


}
