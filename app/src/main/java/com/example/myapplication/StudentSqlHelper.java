package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StudentSqlHelper extends SQLiteOpenHelper {

    public StudentSqlHelper(Context context){
        super(context, "tencuafile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE = "CREATE TABLE student(id INTEGER PRIMARY KEY, name NVARCHAR(50),email VARCHAR(50), phone VARCHAR(10))";
        db.execSQL(TABLE);
    }

    public void insert(Student student){
        //B1: ghep gia tri can them voi cot tuong ung thong qua class ContentValues
        ContentValues values = new ContentValues();
        values.put("id", student.getId()+"");//int -> String
        values.put("name", student.getName());
        values.put("email", student.getEmail());
        values.put("phone", student.getPhone());
        //B2: Thuc hien insert bang ham insert trong thu vien sqlite
        long ketqua = getWritableDatabase().insert("student", null,values);
        //B3: Kiem tra thanh cong / that bai
        if (ketqua > 0){
            Log.e("AAA","Them thanh cong");
        }else {
            Log.e("AAA", "Them that bai");
        }
    }

    public void update(Student student){
        //B1: ghep gia tri can them voi cot tuong ung thong qua class ContentValues
        ContentValues values = new ContentValues();
        values.put("id", student.getId()+"");//int -> String
        values.put("name", student.getName());
        values.put("email", student.getEmail());
        values.put("phone", student.getPhone());
        //B2: Thuc hien update bang ham update trong thu vien sqlite
        long ketqua = getWritableDatabase().update("student",values,"id=?",new String[]{student.getId()+""});
        //B3: Kiem tra thanh cong / that bai
        if (ketqua > 0){
            Log.e("AAA","Sua thanh cong");
        }else {
            Log.e("AAA", "Sua that bai");
        }
    }
    public void Delete(int id){
        //B1: ghep gia tri can them voi cot tuong ung thong qua class ContentValues
        //B2: Thuc hien update bang ham update trong thu vien sqlite
        long ketqua = getWritableDatabase().delete("student","id=?",new String[]{id+""});
        //B3: Kiem tra thanh cong / that bai
        if (ketqua > 0){
            Log.e("AAA","Xoa thanh cong");
        }else {
            Log.e("AAA", "Xoa that bai");
        }
    }

    public List<Student> getAll(){
        List<Student> alls = new ArrayList<>();
        String sql = "SELECT * FROM student";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phone = cursor.getString(3);
                Student student = new Student(id,name,email,phone);
                alls.add(student);
            }
            while (cursor.moveToNext());
        }
        return alls;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
