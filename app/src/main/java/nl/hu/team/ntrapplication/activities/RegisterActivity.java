package nl.hu.team.ntrapplication.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import nl.hu.team.ntrapplication.R;

public class RegisterActivity extends ActionBarActivity {

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
}
