package com.chatapp.chatapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context){
        super(context, "faqbot.db",null ,1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE login (" +
                   "id VARCHAR(36))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
