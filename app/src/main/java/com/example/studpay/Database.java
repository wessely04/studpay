package com.example.studpay;

import android.content.ContentValues;
import  android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String qry1="create table users(username text,password text)";
           sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username,String pass){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",pass);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String email,String pass) {
        int result = 0;
        String  []str = new String[2];
        str[0]=email;
        str[1]=pass;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
          c.close();
        return result;
    }
}
