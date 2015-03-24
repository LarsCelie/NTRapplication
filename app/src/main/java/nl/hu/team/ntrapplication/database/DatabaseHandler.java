package nl.hu.team.ntrapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.objects.Research;
import nl.hu.team.ntrapplication.objects.Survey;

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
    private static final String FK_ID_R = "id_r";

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
    private static String FK_ID_S = "id_s";

    /**
     * OPTION
     */
    //TABLE
    private static final String TABLE_OPTION_O = "OPTION";
    //COLUMNS
    private static String ID_O = "id";
    private static String CONTENT_O = "content";
    private static String VALUE_O = "value";
    private static String FK_ID_QQ = "id_q";

    /**
     * ATTACHMENT
     */
    // TABLE
    private static final String TABLE_ATTACHMENT_A = "ATTACHMENT";
    // COLUMNS
    private static final String ID_A = "id";
    private static final String TYPE_A = "type";
    private static final String LOCATION_A = "location";
    private static final String FK_ID_Q = "id_q";

    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESEARCH_TABLE = "CREATE TABLE " + TABLE_RESEARCH_R + "("
                + ID_R + " INTEGER PRIMARY KEY, " + NAME_R + " TEXT, "
                + STATUS_R + " TEXT, " + BEGINDATE_R + " TEXT, "
                + ENDDATE_R + " TEXT " + ");";
        String CREATE_SURVEY_TABLE = "CREATE TABLE " + TABLE_SURVEY_S + "("
                + ID_S + " INTEGER PRIMARY KEY, " + NAME_S + " TEXT, "
                + BEGINDATE_S + " TEXT, " + ENDDATE_S + " TEXT, "
                + STATUS_S + " TEXT, " + FK_ID_R + " TEXT," + " FOREIGN KEY(" + FK_ID_R + ") REFERENCES " + TABLE_RESEARCH_R + "("+ ID_R +")" + ");";
        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION_Q + "("
                + ID_Q + " INTEGER PRIMARY KEY, " + DESCRIPTION_Q + " TEXT, "
                + SEQUENCE_Q + " INTEGER, " + TYPE_Q + " TEXT, " + FK_ID_S + " TEXT," + " FOREIGN KEY(" + FK_ID_S + ") REFERENCES " + TABLE_SURVEY_S + "("+ ID_S +")" + ");";
        String CREATE_OPTION_TABLE = "CREATE TABLE " + TABLE_OPTION_O + "("
                + ID_O + " INTEGER PRIMARY KEY, " + CONTENT_O + " TEXT, "
                + VALUE_O + " TEXT, " + FK_ID_QQ + " TEXT," + " FOREIGN KEY(" + FK_ID_QQ + ") REFERENCES " + TABLE_QUESTION_Q + "("+ID_Q+")" + ");";
        String CREATE_ATTACHMENT_TABLE = "CREATE TABLE " + TABLE_ATTACHMENT_A + "("
                + ID_A + " INTEGER PRIMARY KEY, " + TYPE_A + " TEXT, "
                + LOCATION_A + " TEXT, " + FK_ID_Q + " TEXT, " + "FOREIGN KEY(" + FK_ID_Q + ") REFERENCES " + TABLE_QUESTION_Q + "(" + ID_Q + ")" + ");";


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

    public void addResearch(Research research) {

    }
    public void addSurvey(Survey survey) {

    }
    public void addQuestion(Question question) {

    }
    public void addOption(Option option) {

    }
    public void addAttachment(Attachment attachment, Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_A, attachment.getID());
        values.put(TYPE_A, attachment.getTYPE());
        values.put(LOCATION_A, attachment.getLOCATION());
        values.put(FK_ID_Q, question.getId());

        db.insert(TABLE_ATTACHMENT_A,null,values);
        db.close();
    }
    public Research getResearchByID(int ID) {
        return null;
    }
    public Survey getSurveyByID(int ID) {
        return null;
    }
    public Question getQuestionByID(int ID) {
        return null;
    }
    public Option getOptionByID(int ID) {
        return null;
    }
    public Attachment getAttachmentByID(int ID) {
        String selectQuery = "SELECT * FROM " + TABLE_ATTACHMENT_A + " WHERE " + ID_A + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            Attachment attachment = new Attachment();
            attachment.setID(Integer.parseInt(cursor.getString(0)));
            attachment.setTYPE(cursor.getString(1));
            attachment.setLOCATION(cursor.getString(2));
            return attachment;
        }
       return null;
    }
}
