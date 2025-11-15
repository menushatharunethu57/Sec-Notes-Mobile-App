package com.example.secnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "PassDB";
    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE storage(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " topic TEXT," +
                " content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS storage");
        onCreate(db);
    }

    public long insertData(String topic, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("topic",topic);
        cv.put("content",content);
        return db.insert("storage",null,cv);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM storage",null);
    }

    public Cursor getDataById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM storage WHERE id = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateData(Integer id, String topic, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("topic", topic);
        cv.put("content", content);

        int result = db.update("storage", cv, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public boolean deleteData(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result =  db.delete("storage","id = ?",new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
}
