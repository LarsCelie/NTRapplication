package nl.hu.team.ntrapplication.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;

public class InlogActivity extends ActionBarActivity {

    private TextView username, password;
    private EditText usernameEdit, passwordEdit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlog);

        username = (TextView) findViewById(R.id.gebruikersnaam);
        password = (TextView) findViewById(R.id.wachtwoord);
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
}
