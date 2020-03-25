package com.example.sleavesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.content.DialogInterface;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class FacultyProfile1 extends AppCompatActivity {

    Database db1;
    public SQLiteDatabase db, wdb;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;
    ListView listView;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String preferences = "";
    public ListView listview;
    public int faculty_id;
    ArrayList<students_data> arrayList = new ArrayList<>();
    CustomAdapter adapter;
    Attendnce_adapter att_adapter;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faculty_profile1);

        db1 = new Database(getApplicationContext(), "LeaveSystem", null, 6);


        sp = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        faculty_id = sp.getInt("facultyid", 0);


        listview = (ListView) findViewById(R.id.listview1);

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ArrayList<students_data> arrayList = new ArrayList<>();

                db = db1.getReadableDatabase();
                cursor = db.rawQuery("select leaves_info.PNR,reason,date_from,date_to,no_of_leaves,allowed from leaves_info left join students on leaves_info.PNR=students.PNR where course_code =(select course_code from class_teacher where _id=" + faculty_id + ")", null);


                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(new students_data(cursor.getInt(0), cursor.getInt(3), cursor.getInt(2), cursor.getInt(4), cursor.getString(1), cursor.getString(5)));
                        adapter = new CustomAdapter(getApplicationContext(), arrayList);
                   /* String[] from = {"PNR", "date_to", "date_from", "no_of_leaves", "reason", "allowed"};
                    int[] to = {R.id.textView16, R.id.textView18, R.id.textView19, R.id.textView20, R.id.textView21, R.id.textView32};
                    cursorAdapter = new SimpleCursorAdapter(
                            getApplicationContext(),
                            R.layout.leave_details,
                            cursor,
                            from,
                            to,
                            0
                    ); */

                    } while (cursor.moveToNext());

                    listview.setAdapter(adapter);


                }
            }

        });

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = db1.getReadableDatabase();
                 ArrayList<students_data> arrayList1 = new ArrayList<>();
                ArrayList<students_data> arrayList2 = new ArrayList<>();

                cursor = db.rawQuery("select * from students where course_code=(select course_code from class_teacher where _id=" + faculty_id + ")", null);
                if (cursor.getCount()>0) {
                    int i = 100,j=50;
                    while (cursor.moveToNext()) {
                        e1=(EditText)findViewById(R.id.editText6);
                        e2=(EditText)findViewById(R.id.editText5);
                        arrayList1.add(new students_data(cursor.getInt(1), cursor.getString(2), cursor.getInt(5),0,0));
                        att_adapter = new Attendnce_adapter(getApplicationContext(), arrayList1);
                          // arrayList1.add(new students_data(String.valueOf(50),String.valueOf(100)));


                   /* String[] from = {"PNR", "date_to", "date_from", "no_of_leaves", "reason", "a      llowed"};
                    int[] to = {R.id.textView16, R.id.textView18, R.id.textView19, R.id.textView20, R.id.textView21, R.id.textView32};
                    cursorAdapter = new SimpleCursorAdapter(
                            getApplicationContext(),
                            R.layout.leave_details,
                            cursor,
                            from,
                            to,
                            0
                    ); */

                    }
                }
                listview.setAdapter(att_adapter);

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

                SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(FacultyProfile1.this, MainActivity.class);
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