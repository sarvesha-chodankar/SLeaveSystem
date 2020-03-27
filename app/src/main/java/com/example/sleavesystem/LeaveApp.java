package com.example.sleavesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.sleavesystem.StudentProfile.preferences;

public class LeaveApp extends AppCompatActivity {
    Button b1;
    public EditText e1,e2,e3;
    DatePickerDialog picker;
    Spinner s1;
    Database db1;
    SQLiteDatabase db;
    SharedPreferences sp;
    public static String studentid;
    SharedPreferences.Editor editor;
    public static final String preferences="";
    public int student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_app);

        b1=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        e3=(EditText)findViewById(R.id.editText);

        db1=new Database(getApplicationContext(),"LeaveSystem",null,7);
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                picker= new DatePickerDialog(LeaveApp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        e1.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                final int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                picker= new DatePickerDialog(LeaveApp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        e2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = db1.getWritableDatabase();

                s1 = (Spinner) findViewById(R.id.spinner);

                sp=getSharedPreferences(preferences, Context.MODE_PRIVATE);
                student_id=sp.getInt("studentid",0);
                ContentValues cv = new ContentValues();

                cv.put("date_from", e1.getText().toString());
                cv.put("date_to", e2.getText().toString());
                cv.put("no_of_leaves", e3.getText().toString());
                cv.put("reason", (String) s1.getSelectedItem());
                cv.put("allowed", "No");
                cv.put("PNR",student_id );
                long rowinserted = db.insert("leaves_info", null, cv);
                if (rowinserted == -1)
                    Toast.makeText(getApplicationContext(), "unsuccesful" + e2.getText() + "", Toast.LENGTH_LONG).show();
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LeaveApp.this).create();
                    alertDialog.setTitle("ALERT!");
                    alertDialog.setMessage("Application submited!");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                Toast.makeText(getApplicationContext(), "inserted" + e2.getText() + "", Toast.LENGTH_LONG).show();
                db.close();

            }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item1:

                SharedPreferences preferences =getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
