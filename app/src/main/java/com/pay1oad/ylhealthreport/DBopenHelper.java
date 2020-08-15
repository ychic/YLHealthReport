package com.pay1oad.ylhealthreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBopenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    private InnerDateBase.CreateDB createDB;


    public class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createDB.getCreate0());
            db.execSQL(createDB.getCreate1());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + createDB.getTablename0());
            db.execSQL("DROP TABLE IF EXISTS " + createDB.getTablename1());
            onCreate(db);
        }
    }

    public DBopenHelper(Context context) {
        this.mCtx = context;
    }

    public DBopenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }

    public void close() {
        mDB.close();
    }


    //insert
    public long insertColumn(String userid, String name, long age, String gender, String table_type) {
        ContentValues values = new ContentValues();
        /*
            set Insert SQL Container
            values.input (Column , data )
        */
        return mDB.insert(createDB.getTablename0(), null, values);
    }

    //update
    public boolean updateColumn(long id, String userid, String name, long age , String gender){
        ContentValues values = new ContentValues();

        /*
            set Insert SQL Container
            values.input (Column , data )
        */

        return mDB.update(createDB.getTablename0(), values, "_id=" + id, null) > 0;
    }

    //select
    public Cursor selectColumns(String query){

        return mDB.rawQuery(query, null, null);
    }


    // Delete All
    public void deleteAllColumns() {
        mDB.delete(createDB.getTablename0(), null, null);
    }

    // Delete Column
    public boolean deleteColumn(long id){
        return mDB.delete(createDB.getTablename0(), "_id="+id, null) > 0;
    }
}
