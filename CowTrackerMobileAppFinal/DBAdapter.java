package com.example.cowlogs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_COW = "cow";
    static final String KEY_CONDITION = "condition";
    static final String KEY_WEIGHT = "weight";
    static final String KEY_AGE = "age";
    static final String KEY_DAY = "day";
    static final String KEY_MONTH = "month";
    static final String KEY_YEAR = "year";
    static final String KEY_HOUR = "hour";
    static final String KEY_MINUTE = "minute";
    static final String KEY_SECOND = "second";
    static final String KEY_LONGITUDE = "longitude";
    static final String KEY_LATITUDE = "latitude";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "cowDB";
    static final String DATABASE_TABLE = "cowTable";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table cowTable (_id integer primary key autoincrement, " + "cow integer not null, condition text not null, weight text not null, age text not null, day integer not null, month integer not null, year integer not null, hour integer not null, minute integer not null, second integer not null, longitude text not null, latitude text not null);";
    final Context context;
    static DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE);
                db.execSQL(DATABASE_CREATE);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerion){
            Log.w(TAG, "Upgrading databse from verion " + oldVersion + " to " + newVerion + ", which will destroy old data!");
            db.execSQL("DROP TABLE IF EXISTS cowTable");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public long insertCows(int cow, String condition, String weight, String age, int day, int month, int year, int hour, int minute, int second, String longitude, String latitude){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_COW, cow);
        initialValues.put(KEY_CONDITION, condition);
        initialValues.put(KEY_WEIGHT, weight);
        initialValues.put(KEY_AGE, age);
        initialValues.put(KEY_DAY, day);
        initialValues.put(KEY_MONTH, month);
        initialValues.put(KEY_YEAR, year);
        initialValues.put(KEY_HOUR, hour);
        initialValues.put(KEY_MINUTE, minute);
        initialValues.put(KEY_SECOND, second);
        initialValues.put(KEY_LONGITUDE, longitude);
        initialValues.put(KEY_LATITUDE, latitude);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteCows(long rowId){
        return db.delete(DATABASE_TABLE, KEY_ROWID + " = " + rowId, null) > 0;
    }

    public Cursor getAllCows(){
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_COW, KEY_CONDITION, KEY_WEIGHT, KEY_AGE, KEY_DAY, KEY_MONTH, KEY_YEAR, KEY_HOUR, KEY_MINUTE, KEY_SECOND, KEY_LONGITUDE, KEY_LATITUDE}, null, null, null, null, null);
    }

    public Cursor getCow(long rowId) throws SQLException{
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_COW, KEY_CONDITION, KEY_WEIGHT, KEY_AGE, KEY_DAY, KEY_MONTH, KEY_YEAR, KEY_HOUR, KEY_MINUTE, KEY_SECOND, KEY_LONGITUDE, KEY_LATITUDE}, KEY_ROWID + " = " + rowId, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateCow(long rowId, int cow, String weight, String age, String condition, int day, int month, int year, int hour, int minute, int second, String longitude, String latitude){
        ContentValues args = new ContentValues();
        args.put(KEY_COW, cow);
        args.put(KEY_CONDITION, condition);
        args.put(KEY_WEIGHT, weight);
        args.put(KEY_AGE, age);
        args.put(KEY_DAY, day);
        args.put(KEY_MONTH, month);
        args.put(KEY_YEAR, year);
        args.put(KEY_HOUR, hour);
        args.put(KEY_MINUTE, minute);
        args.put(KEY_SECOND, second);
        args.put(KEY_LONGITUDE, longitude);
        args.put(KEY_LATITUDE, latitude);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + " = " + rowId, null) > 0;
    }

}
