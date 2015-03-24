package nl.hu.team.ntrapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import nl.hu.team.ntrapplication.database.DatabaseHandler;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Question;


public class MainActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        // TEST DATA
        Attachment a = new Attachment();
        a.setID(1);
        a.setLOCATION("iets");
        a.setTYPE("image");

        Question q = new Question();
        q.setId(1);
        q.setDescription("blabla");
        q.setSequence(1);
        q.setType("blablabla");
        db.addAttachment(a,q);

        Attachment test = db.getAttachmentByID(1);
        String s = test.getTYPE();

        textView = (TextView) findViewById(R.id.textviewtje);
        textView.setText(s);
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
