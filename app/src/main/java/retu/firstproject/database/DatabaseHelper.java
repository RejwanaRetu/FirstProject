package retu.firstproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import retu.firstproject.Model.Student;

/**
 * Created by Retu on 13-10-17.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SRM";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableAttributes object = new TableAttributes();
        String query = object.TableCreation();
        try {
            db.execSQL(query);
            Log.i("Table create", "Done");
        } catch (SQLException e) {
            Log.e("SQL error", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerNewStudent(Student student) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableAttributes.STUDENT_NAME, student.getName());
        contentValues.put(TableAttributes.STUDENT_ID, student.getId());
        contentValues.put(TableAttributes.STUDENT_PASSWORD, student.getPassword());
        contentValues.put(TableAttributes.STUDENT_EMAIL, student.getEmail());
        contentValues.put(TableAttributes.STUDENT_PHONE_NUMBER, student.getPhone());
        contentValues.put(TableAttributes.STUDENT_CGPA, student.getCgpa());
        contentValues.put(TableAttributes.STUDENT_GENDER, student.getGender());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(TableAttributes.TABLE_NAME, null, contentValues);
            Log.i("Insert Value", "Done");
        } catch (SQLException e) {
            Log.e("Insert Error", e.toString());
        }
    }

    public String getPassByMail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT password From " + TableAttributes.TABLE_NAME + " Where " + TableAttributes.STUDENT_EMAIL + " = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return null;
        } else {
            cursor.moveToFirst();
            String pass = cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PASSWORD));
            cursor.close();
            return pass;
        }
    }

    public Student getSingleStudentByEmail(String email) {
        Student student = new Student();
        String query = "SELECT * From " + TableAttributes.TABLE_NAME + " Where " + TableAttributes.STUDENT_EMAIL + " = '" + email + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        student.setName(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_NAME)));
        student.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PASSWORD)));
        student.setId(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_ID)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_EMAIL)));
        student.setPhone(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PHONE_NUMBER)));
        student.setGender(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_GENDER)));
        student.setCgpa(Double.parseDouble(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_CGPA))));

        cursor.close();
        return student;
    }

    public ArrayList<Student> getAllStudent() {

        ArrayList<Student> students = new ArrayList<Student>();
        String query = "SELECT * from " + TableAttributes.TABLE_NAME + " ";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.setName(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_NAME)));
                student.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PASSWORD)));
                student.setId(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_ID)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_EMAIL)));
                student.setPhone(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PHONE_NUMBER)));
                student.setGender(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_GENDER)));
                student.setCgpa(Double.parseDouble(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_CGPA))));
                students.add(student);
                cursor.moveToNext();
                }
            }
            cursor.close();
            return students;
    }
}