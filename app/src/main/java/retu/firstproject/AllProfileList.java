package retu.firstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retu.firstproject.Model.Student;
import retu.firstproject.adapter.CustomAdapter;
import retu.firstproject.database.DatabaseHelper;

import static retu.firstproject.R.drawable.email;

public class AllProfileList extends AppCompatActivity {
    ListView listViewAllStudents;
    ArrayList<Student> arrayListStudent;
    ArrayAdapter<Student> arrayAdepterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_profile_list);

        listViewAllStudents = (ListView)findViewById(R.id.listViewAllProfile);
        arrayListStudent = new ArrayList<Student>();

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        DatabaseHelper databaseHelper = new DatabaseHelper(AllProfileList.this);
        arrayListStudent = databaseHelper.getAllStudent();

        for(int i = 0; i < arrayListStudent.size(); i++){
            if(arrayListStudent.get(i).getEmail().equals(email)){
                arrayListStudent.remove(arrayListStudent.get(i));
                break;
            }
        }

       CustomAdapter customAdapter = new CustomAdapter(AllProfileList.this, arrayListStudent);

//        arrayAdepterStudent = new ArrayAdapter<Student>(AllProfileList.this, android.R.layout.simple_list_item_1, arrayListStudent);

        listViewAllStudents.setAdapter(customAdapter);
        arrayAdepterStudent.notifyDataSetChanged();
    }
}
