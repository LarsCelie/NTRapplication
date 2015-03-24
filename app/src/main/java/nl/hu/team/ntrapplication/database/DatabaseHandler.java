package nl.hu.team.ntrapplication.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiry on 24-3-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NTRApplicationDB";

    /**
     * RESEARCH
     */
    // TABLE
    private static final String TABLE_RESEARCH = "RESEARCH";
    // COLUMNS
    private static final String ID = "ID";

    /**
     * SURVEY
     */
    // TABLE
    private static final String TABLE_SURVEY = "SURVEY";
    // COLUMNS

    /**
     * QUESTION
     */
    //TABLE
    private static final String TABLE_QUESTION = "QUESTION";
    //COLUMNS
    private static String DESCRIPTION = "DESCRIPTION";
    private static String SEQUENCE = "SEQUENCE";
    private static String TYPE = "TYPE";

    /**
     * OPTION
     */
    //TABLE
    private static final String TABLE_OPTION = "OPTION";
    //COLUMNS
    private static String CONTENT = "CONTENT";
    private static String VALUE = "VALUE";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
