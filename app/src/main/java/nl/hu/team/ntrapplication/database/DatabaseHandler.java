package nl.hu.team.ntrapplication.database;

import android.content.Context;
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
    private static final String TABLE_RESEARCH_R = "RESEARCH";
    // COLUMNS
    private static final String ID_R = "id";
    private static final String NAME_R = "name";
    private static final String BEGINDATE_R = "beginDate";
    private static final String ENDDATE_R = "endDate";
    private static final String STATUS_R = "status";

    /**
     * SURVEY
     */
    // TABLE
    private static final String TABLE_SURVEY_S = "SURVEY";
    // COLUMNS
    private static final String ID_S = "id";
    private static final String NAME_S = "name";
    private static final String BEGINDATE_S = "beginDate";
    private static final String ENDDATE_S = "endDate";
    private static final String STATUS_S = "status";

    /**
     * QUESTION
     */
    //TABLE
    private static final String TABLE_QUESTION_Q = "QUESTION";
    //COLUMNS
    private static String DESCRIPTION_Q = "description";
    private static String SEQUENCE_Q = "sequence";
    private static String TYPE_Q = "type";

    /**
     * OPTION
     */
    //TABLE
    private static final String TABLE_OPTION_O = "OPTION";
    //COLUMNS
    private static String CONTENT_O = "content";
    private static String VALUE_O = "value";

    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
