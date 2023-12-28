package com.example.tuan7bt1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) { super(context,"TodoDatabase",null,1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TODO(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TITLE TEXT, CONTENT TEXT, DATE TEXT, TYPE TEXT, STATUS INTEGER)";
            db.execSQL(sql);
            String data = "INSERT INTO TODO VALUES(1,'Hoc Java', 'Hoc java co ban', '27/2/2023', 'Binh Thuong', 1),"+
                    "(3, 'Hoc HTML', 'Hoc HTML co ban', '24/3/2023', 'kho', 0),"+
                    "(4, 'Hoc HTML', 'Hoc HTML co ban', '24/3/2023', 'de', 0)";
            db.execSQL(data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE TODO");
            onCreate(db);
        }

    }
}
