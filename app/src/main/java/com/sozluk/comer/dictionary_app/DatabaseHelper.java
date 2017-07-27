package com.sozluk.comer.dictionary_app;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by comer on 20.07.2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private static final String DB_PATH= "/data/data/com.example.comer.dictionary_app/databases/"
            ;
    private static final String DB_NAME="sozluk.db";
    private SQLiteDatabase sqliteDatabase;
    private Context mContext;
    public DatabaseHelper(Context context){

        super (context,DB_NAME,null,VERSION);
        mContext=context;

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }



    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {

            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {

                throw new Error("Veritabanı kopyalanamadı");

            }}}



    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }public SQLiteDatabase getDatabase(){
        return sqliteDatabase;
    } public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        sqliteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }


    @Override
    public synchronized void close() {
        if (sqliteDatabase != null)
            sqliteDatabase.close();
        super.close();
    }}