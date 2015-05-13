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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import nl.hu.team.ntrapplication.R;

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
        //String Vfirstname = voornaam.getText().toString();
       // String Vlastname = achternaam.getText().toString();
        //String Vemail = email.getText().toString();
        String Vusername = username.getText().toString();
        String Vpassword = password.getText().toString();
       // String Vpasswordcheck = passwordcheck.getText().toString();

        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();

        // Http parameters
      //  params.put("voornaam", Vfirstname);
      //  params.put("achternaam", Vlastname);
      //  params.put("email", Vemail);
        params.put("username", Vusername);
        params.put("password", Vpassword);
       // params.put("passwordcheck", Vpasswordcheck);
        invokeWS(params);
    }


    // Method that performs RESTful webservice invocations
    public void invokeWS(RequestParams params) {

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://localhost:8080/NTR_application/rest/session/create", params, new AsyncHttpResponseHandler() {

            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statuscode, Header[] headers, byte[] response) {
                Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                // Navigate to Home screen
                navigatetoLoginActivity();
            }

            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statuscode, Header[] headers, byte[] errorResponse, Throwable throwable) {

                Toast.makeText(getApplicationContext(), "ERROR!" + statuscode, Toast.LENGTH_LONG).show();
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
