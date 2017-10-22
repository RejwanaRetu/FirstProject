package retu.firstproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import retu.firstproject.Model.Student;
import retu.firstproject.R;

/**
 * Created by Retu on 21-10-17.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Student> students;

    public CustomAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_view = layoutInflater.inflate(R.layout.custom_adapter_layout, null);

        TextView textViewName = (TextView) custom_view.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) custom_view.findViewById(R.id.textViewEmail);
        TextView textViewCgpa = (TextView) custom_view.findViewById(R.id.textViewCgpa);

        textViewName.setText(students.get(position).getName());
        textViewEmail.setText(students.get(position).getEmail());
        textViewCgpa.setText(String.valueOf(students.get(position).getCgpa()));

        return custom_view;
    }
}
