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
    private static String ID_Q = "id";
    private static String DESCRIPTION_Q = "description";
    private static String SEQUENCE_Q = "sequence";
    private static String TYPE_Q = "type";

    /**
     * OPTION
     */
    //TABLE
    private static final String TABLE_OPTION_O = "OPTION";
    //COLUMNS
    private static String ID_O = "id";
    private static String CONTENT_O = "content";
    private static String VALUE_O = "value";

    /**
     * ATTACHMENT
     */
    // TABLE
    private static final String TABLE_ATTACHMENT_A = "ATTACHMENT";
    // COLUMNS
    private static final String ID_A = "id";
    private static final String TYPE_A = "type";
    private static final String LOCATION_A = "location";

    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESEARCH_TABLE = "CREATE TABLE " + TABLE_RESEARCH_R + "("
                + ID_R + " INTEGER PRIMARY KEY, " + NAME_R + " TEXT, "
                + STATUS_R + " TEXT, " + BEGINDATE_R + " TEXT, "
                + ENDDATE_R + " TEXT " + ")";
        String CREATE_SURVEY_TABLE = "CREATE TABLE " + TABLE_SURVEY_S + "("
                + ID_S + "INTEGER PRIMARY KEY, " + NAME_S + "TEXT, "
                + BEGINDATE_S + " TEXT, " + ENDDATE_S + " TEXT, "
                + STATUS_S + " TEXT " + ")";
        String CREATE_QUESTION_TABLE = "CREATE_TABLE" + TABLE_QUESTION_Q + "("
                + ID_Q + "INTEGER PRIMARY KEY, " + DESCRIPTION_Q + " TEXT, "
                + SEQUENCE_Q + " INTEGER, " + TYPE_Q + " TEXT" + ")";
        String CREATE_OPTION_TABLE = "CREATE_TABLE" + TABLE_OPTION_O + "("
                + ID_O + " INTEGER PRIMARY KEY, " + CONTENT_O + " TEXT, "
                + VALUE_O + " TEXT" + ")";
        String CREATE_ATTACHMENT_TABLE = "CREATE TABLE " + TABLE_ATTACHMENT_A + "("
                + ID_A + " INTEGER PRIMARY KEY, " + TYPE_A + " TEXT, "
                + LOCATION_A + " TEXT " + ")";


        db.execSQL(CREATE_RESEARCH_TABLE);
        db.execSQL(CREATE_SURVEY_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);
        db.execSQL(CREATE_OPTION_TABLE);
        db.execSQL(CREATE_ATTACHMENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESEARCH_R);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SURVEY_S);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_Q);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTION_O);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTACHMENT_A);

        // Create tables again
        onCreate(db);
    }
}
