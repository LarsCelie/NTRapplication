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
import com.loopj.android.http.RequestParams;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.User;
import nl.hu.team.ntrapplication.services.Utility;

public class InlogActivity extends Activity {

    private EditText usernameEdit, passwordEdit;
    private Button login;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlog);

        usernameEdit = (EditText) findViewById(R.id.gebruikersnaamtext);
        passwordEdit = (EditText) findViewById(R.id.wachtwoortext);
        login = (Button) findViewById(R.id.loginbutton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inlog, menu);
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


    // Method gets triggered when login button is clicked
    public void loginUser(View view) {

        // Get username and password values
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();

        // Check if username & password is not null
        if(Utility.isNotNull(username) && Utility.isNotNull(password)) {

            // Http parameters
            params.put("username", username);
            params.put("password", password);
            invokeWS(params);

        } else {
            Toast.makeText(getApplicationContext(), "Vul een gebruikersnaam en of " +
                    "wachtwoord in", Toast.LENGTH_LONG).show();
        }
    }

    // Method that performs RESTful webservice invocations
    public void invokeWS(RequestParams params) {

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        client.post("http://10.0.2.2:8080/NTR_application/rest/session", params, new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), "You are successfully logged in!" + response, Toast.LENGTH_LONG).show();

                // Gets an JSON object with user Data
                // Write user Data to SQLite
                User user = new Gson().fromJson(response, User.class);
                db.addUser(user);

                // Navigate to Home screen
                navigatetoHomeActivity();
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                Toast.makeText(getApplicationContext(), "ERROR!" + content + error + statusCode, Toast.LENGTH_LONG).show();
            }
        });


    }


    // Method gets triggered when register button is clicked
    public void navigateToRegisterActivity(View view) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(registerIntent);
    }


    // Method which navigates from Login Activity to Home Activity
    public void navigatetoHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(),MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

}
