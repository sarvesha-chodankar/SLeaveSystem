package com.example.sleavesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EnterAttendance extends AppCompatActivity {
    Button b1;
    EditText e1, e2;
    Spinner s1;
    Database db1;
    SQLiteDatabase db, rdb;
    SharedPreferences sp;
    public static String studentid;
    SharedPreferences.Editor editor;
    public static final String preferences = "";
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;
    public int faculty_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_attendance);

        b1 = (Button) findViewById(R.id.button11);
        e1 = (EditText) findViewById(R.id.editText5);
        e2 = (EditText) findViewById(R.id.editText6);
        s1 = (Spinner) findViewById(R.id.spinner2);

        db1 = new Database(getApplicationContext(), "LeaveSystem", null, 7);

        rdb = db1.getReadableDatabase();

        sp = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        faculty_id = sp.getInt("facultyid", 0);
        cursor = rdb.rawQuery("select PNR from students where course_code=(select course_code from class_teacher where _id=" + faculty_id + ")", null);
        List<String> names = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            Toast.makeText(getApplicationContext(), "spinner", Toast.LENGTH_LONG).show();
            do {
                names.add(String.valueOf(cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        else
            Toast.makeText(getApplicationContext(), "No spinner", Toast.LENGTH_LONG).show();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,names);

        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(dataAdapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                db = db1.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put("Attendance_count", e2.getText().toString());
                cv.put("total_attendance", e1.getText().toString());
                double percentge=0.0;
                cursor = rdb.rawQuery("select no_of_leaves from leaves_info where PNR=" + s1.getSelectedItem(), null);
                if(cursor.moveToFirst())
                {
                    double x=Double.parseDouble(e2.getText().toString());
                    double y=(Double.parseDouble(e1.getText().toString())-cursor.getInt(0));
                    percentge=(x*100)/y;
                    cv.put("A_percentage",percentge);
                    Toast.makeText(getApplicationContext(), "Attendance %= " + percentge + "", Toast.LENGTH_LONG).show();

                }
                else
                {
                    percentge=(Double.parseDouble(e2.getText().toString())*100)/Double.parseDouble(e1.getText().toString());
                    cv.put("A_percentage",percentge);
                    Toast.makeText(getApplicationContext(), "Attendance %= " + percentge + "", Toast.LENGTH_LONG).show();

                }
                long rowinserted = db.update("students", cv,"PNR= "+s1.getSelectedItem(),null);
                if (rowinserted != -1)
                    Toast.makeText(getApplicationContext(), "Attendance added" + e2.getText() + "", Toast.LENGTH_LONG).show();
            }
        });

    }
}