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
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BEGINDATE = "beginDate";
    private static final String ENDDATE = "endDate";
    private static final String STATUS = "status";

    /**
     * SURVEY
     */
    // TABLE
    private static final String TABLE_SURVEY = "SURVEY";
    // COLUMNS

    private static final String TABLE_QUESTION = "QUESTION";
    /**
     * OPTION
     */
    private static final String TABLE_OPTION = "OPTION";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
