package com.example.sleavesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    public EditText e1,e2,e3,e4,e5;
    DatePickerDialog picker;
    Spinner s1;
    Database db1;
    SQLiteDatabase db;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String preferences="";
    public int student_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);

        db1 = new Database(getApplicationContext(), "LeaveSystem", null, 7);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = db1.getWritableDatabase();

                ContentValues cv = new ContentValues();

                cv.put("Name", e1.getText().toString());
                cv.put("Mobile_No", e2.getText().toString());
                cv.put("Email", e3.getText().toString());
                cv.put("PNR", e4.getText().toString());
                cv.put("course_code", e5.getText().toString());

                long rowinserted = db.insert("students",null,cv);
                if (rowinserted == -1)
                    Toast.makeText(getApplicationContext(), "unsuccesful" + e2.getText() + "", Toast.LENGTH_LONG).show();
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddStudent.this).create();
                    alertDialog.setTitle("ALERT!");
                    alertDialog.setMessage("Student Added!");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                    db.close();
            }}
        });
    }
}
