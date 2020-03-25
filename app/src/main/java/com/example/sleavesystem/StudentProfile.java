package com.example.sleavesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sleavesystem.FacultyLogin.preferences;
import static com.example.sleavesystem.studentLogin.studentid;

public class StudentProfile extends AppCompatActivity {
    Button b1,b2,b3;
    Menu logout,setting;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String preferences="";
    public ListView listview;
    Database db1;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;
    public int student_id;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        b1=(Button)findViewById(R.id.button4);
        b3=(Button)findViewById(R.id.button6);
        db1=new Database(getApplicationContext(),"LeaveSystem",null,6);

        sp=getSharedPreferences(preferences, Context.MODE_PRIVATE);
         student_id=sp.getInt("studentid",0);
        listview = (ListView)findViewById(R.id.listview1);
        db = db1.getReadableDatabase();

        cursor = db.rawQuery("select * from students where PNR="+student_id , null);
        int count = cursor.getCount();
        if (count > 0) {
            String[] from = {"PNR","Name","Attendance_count","A_percentage"};
            int[] to = {R.id.textView6,R.id.textView8,R.id.textView9,R.id.textView10};
            cursorAdapter = new SimpleCursorAdapter(
                    this,
                    R.layout.students_details,
                    cursor,
                    from,
                    to,
                    0
            );
            listview.setAdapter(cursorAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "id not found", Toast.LENGTH_LONG).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getSharedPreferences(preferences, Context.MODE_MULTI_PROCESS);
                editor=sp.edit();
                editor.putInt("studentid", student_id);
                editor.commit();
                Intent i=new Intent(getApplicationContext(),LeaveApp.class);
                Toast.makeText(getApplicationContext(), "id"+student_id, Toast.LENGTH_LONG).show();
                startActivity(i);
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
        logout=findViewById(R.id.item1);
        setting=findViewById(R.id.item2);
        switch (id) {
            case R.id.item1:

                SharedPreferences preferences =getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_LONG).show();
                Intent i=new Intent(StudentProfile.this, MainActivity.class);
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
