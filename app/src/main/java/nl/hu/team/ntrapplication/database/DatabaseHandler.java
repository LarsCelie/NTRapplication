package nl.hu.team.ntrapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private static final String BEGIN_DATE_R = "beginDate";
    private static final String END_DATE_R = "endDate";
    private static final String STATUS_R = "status";

    /**
     * SURVEY
     */
    // TABLE
    private static final String TABLE_SURVEY_S = "SURVEY";
    // COLUMNS
    private static final String ID_S = "id";
    private static final String NAME_S = "name";
    private static final String BEGIN_DATE_S = "beginDate";
    private static final String END_DATE_S = "endDate";
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
                + STATUS_R + " TEXT, " + BEGIN_DATE_R + " TEXT, "
                + END_DATE_R + " TEXT " + ");";
        String CREATE_SURVEY_TABLE = "CREATE TABLE " + TABLE_SURVEY_S + "("
                + ID_S + " INTEGER PRIMARY KEY, " + NAME_S + " TEXT, "
                + BEGIN_DATE_S + " TEXT, " + END_DATE_S + " TEXT, "
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

    /**
     * DB CRUD Functions
     */

    public void addResearch(Research research) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_R, research.getID());
        values.put(NAME_R, research.getNAME());
        values.put(STATUS_R, research.getSTATUS());
        values.put(BEGIN_DATE_R, convertDateToString(research.getBEGIN_DATE()));
        values.put(END_DATE_R, convertDateToString(research.getEND_DATE()));

        db.insert(TABLE_RESEARCH_R,null,values);
        db.close();
    }
    public void addSurvey(Survey survey, Research research) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_S, survey.getId());
        values.put(NAME_S, survey.getName());
        values.put(BEGIN_DATE_S, convertDateToString(survey.getBeginDate()));
        values.put(END_DATE_S, convertDateToString(survey.getEndDate()));
        values.put(STATUS_S, survey.getStatus());
        values.put(FK_ID_R, research.getID());

        db.insert(TABLE_SURVEY_S,null,values);
        db.close();
    }
    public void addQuestion(Question question, Survey survey) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_Q, question.getId());
        values.put(DESCRIPTION_Q, question.getDescription());
        values.put(SEQUENCE_Q, question.getSequence());
        values.put(TYPE_Q, question.getType());
        values.put(FK_ID_S, survey.getId());

        db.insert(TABLE_QUESTION_Q,null,values);
        db.close();
    }
    public void addOption(Option option,Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_O, option.getID());
        values.put(CONTENT_O, option.getCONTENT());
        values.put(VALUE_O, option.getVALUE());
        values.put(FK_ID_QQ, question.getId());

        db.insert(TABLE_OPTION_O,null,values);
        db.close();
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
        String selectQuery = "SELECT * FROM " + TABLE_RESEARCH_R + " WHERE " + ID_R  + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            Research research = new Research();
            research.setID(Integer.parseInt(cursor.getString(0)));
            research.setNAME(cursor.getString(1));
            research.setSTATUS(cursor.getString(2));
            research.setBEGIN_DATE(convertStringToDate(cursor.getString(3)));
            research.setEND_DATE(convertStringToDate(cursor.getString(4)));
            return research;
        }
        return null;
    }
    public ArrayList<Research> getAllResearch() {
        ArrayList<Research> researches = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_RESEARCH_R;
        Cursor cursor = db.rawQuery(selectQuery,null);
        while(cursor.moveToNext()) {
            Research research = new Research();
            research.setID(Integer.parseInt(cursor.getString(0)));
            research.setNAME(cursor.getString(1));
            research.setSTATUS(cursor.getString(2));
            research.setBEGIN_DATE(convertStringToDate(cursor.getString(3)));
            research.setEND_DATE(convertStringToDate(cursor.getString(4)));
            for(Survey s : getSurveyByResearch(research)) {
                research.addSurvey(s);
            }
            researches.add(research);
        }
        return researches;
    }
    public Survey getSurveyByID(int ID) {
        String selectQuery = "SELECT * FROM " + TABLE_SURVEY_S + " WHERE " + ID_S + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            Survey survey = new Survey();
            survey.setId(Integer.parseInt(cursor.getString(0)));
            survey.setName(cursor.getString(1));
            survey.setBeginDate(convertStringToDate(cursor.getString(2)));
            survey.setEndDate(convertStringToDate(cursor.getString(3)));
            survey.setStatus(cursor.getString(4));
            return survey;
        }
        return null;
    }
    public ArrayList<Survey> getSurveyByResearch(Research research) {
        String selectQuery = "SELECT * FROM " + TABLE_SURVEY_S + " WHERE " + FK_ID_R + " = " + research.getID();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Survey> surveys = new ArrayList<>();
        while (cursor.moveToNext()) {
            Survey survey = new Survey();
            survey.setId(Integer.parseInt(cursor.getString(0)));
            survey.setName(cursor.getString(1));
            survey.setBeginDate(convertStringToDate(cursor.getString(2)));
            survey.setEndDate(convertStringToDate(cursor.getString(3)));
            survey.setStatus(cursor.getString(4));
            surveys.add(survey);
        }
        return surveys;
    }
    public Question getQuestionByID(int ID) {
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION_Q + " WHERE " + ID_Q + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            Question question = new Question();
            question.setId(Integer.parseInt(cursor.getString(0)));
            question.setDescription(cursor.getString(1));
            question.setSequence(Integer.parseInt(cursor.getString(2)));
            question.setType(cursor.getString(3));
            return question;
        }
        return null;
    }
    public ArrayList<Question> getQuestionBySurvey(Survey survey) {
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION_Q + " WHERE " + FK_ID_S + " = " + survey.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Question> questions = new ArrayList<>();
        while (cursor.moveToNext()) {
            Question question = new Question();
            question.setId(Integer.parseInt(cursor.getString(0)));
            question.setDescription(cursor.getString(1));
            question.setSequence(Integer.parseInt(cursor.getString(2)));
            question.setType(cursor.getString(3));
            questions.add(question);
        }
        return questions;
    }
    public Option getOptionByID(int ID) {
        String selectQuery = "SELECT * FROM " + TABLE_OPTION_O + " WHERE " + ID_O + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            Option option = new Option();
            option.setID(Integer.parseInt(cursor.getString(0)));
            option.setCONTENT(cursor.getString(1));
            option.setVALUE(cursor.getString(2));
            return option;
        }
        return null;
    }
    public ArrayList<Option> getOptionByQuestion(Question question) {
        String selectQuery = "SELECT * FROM " + TABLE_OPTION_O + " WHERE " + FK_ID_QQ + " = " + question.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Option> options = new ArrayList<>();
        while (cursor.moveToNext()) {
            Option option = new Option();
            option.setID(Integer.parseInt(cursor.getString(0)));
            option.setCONTENT(cursor.getString(1));
            option.setVALUE(cursor.getString(2));
            options.add(option);
        }
        return options;
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
    public ArrayList<Attachment> getAttachmentByQuestion(Question question) {
        String selectQuery = "SELECT * FROM " + TABLE_ATTACHMENT_A + " WHERE " + FK_ID_Q + " = " + question.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Attachment> attachments = new ArrayList<>();
        while (cursor.moveToNext()) {
            Attachment attachment = new Attachment();
            attachment.setID(Integer.parseInt(cursor.getString(0)));
            attachment.setTYPE(cursor.getString(1));
            attachment.setLOCATION(cursor.getString(2));
            attachments.add(attachment);
        }
        return attachments;
    }


    private Date convertStringToDate(String input) {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date date = null;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String convertDateToString(Date input) {
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        return format.format(input);
    }
}
