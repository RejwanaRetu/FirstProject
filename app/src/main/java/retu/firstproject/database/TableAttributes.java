package retu.firstproject.database;

/**
 * Created by Retu on 14-10-17.
 */

public class TableAttributes {

    public static final String TABLE_NAME = "student";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_PASSWORD ="password";
    public static final String STUDENT_EMAIL = "email";
    public static final String STUDENT_CGPA = "cgpa";
    public static final String STUDENT_GENDER = "gender";
    public static final String STUDENT_PHONE_NUMBER = "phoneNumber";

    public  String TableCreation() {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + STUDENT_ID + " TEXT, " + STUDENT_NAME + " TEXT, " + STUDENT_PASSWORD + " TEXT, " + STUDENT_EMAIL + " TEXT, " + STUDENT_CGPA + " TEXT, " + STUDENT_GENDER + " TEXT, " + STUDENT_PHONE_NUMBER + " TEXT)";
     return query;
    }
}
