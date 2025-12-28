package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class db extends SQLiteOpenHelper {
    private final String users="users";
    private final String ColumnName="name";
    private final String ColumnID="id";
    private final String ColumnTel="tel";
    public db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+users+"("+ColumnID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ColumnName+" TEXT,"+ColumnTel+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+users);
        onCreate(db);
    }

    public void add(Contact con){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",con.GetName());
        cv.put("tel",con.GetNumber());
        db.insert(users,null,cv);

    }

    public List<Contact> getinfo(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM " +users,null);
        List<Contact> list=new ArrayList<>();
        while(cur.moveToNext()){
            int id=cur.getInt(cur.getColumnIndexOrThrow(ColumnID));
            String name=cur.getString(cur.getColumnIndexOrThrow(ColumnName));
            String tel=cur.getString(cur.getColumnIndexOrThrow(ColumnTel));
            list.add(new Contact(id,name,tel));

        }
        cur.close();
        return list;
    }

    public List<Contact>GetSearch(String pattern){
        List<Contact> list=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
         String selection="name LIKE ? OR tel LIKE ?";
         String[] selectionArgs=new String[]{
                 "%" + pattern + "%",
                 "%" + pattern + "%"
         };

         Cursor cur=db.query(users,null,selection,selectionArgs,null,null,null);
         while(cur.moveToNext()){
             int id=cur.getInt(cur.getColumnIndexOrThrow(ColumnID));
             String n=cur.getString(cur.getColumnIndexOrThrow("name"));
             String t=cur.getString(cur.getColumnIndexOrThrow("tel"));
             list.add(new Contact(id,n,t));
         }
         cur.close();
         return list;
    }

    public void Delete(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(users,ColumnID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void Update(Contact c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnName, c.GetName());
        cv.put(ColumnTel, c.GetNumber());
        db.update(users, cv, ColumnID + "=?", new String[]{String.valueOf(c.GetId())});
        db.close();
    }

    public Contact getContactById(int id){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cur=db.query(users, null, ColumnID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Contact c=null;
        if(cur.moveToFirst()){
            String n=cur.getString(cur.getColumnIndexOrThrow(ColumnName));
            String t=cur.getString(cur.getColumnIndexOrThrow(ColumnTel));
            c=new Contact(id, n, t);
        }
        cur.close();
        return c;
    }




}
