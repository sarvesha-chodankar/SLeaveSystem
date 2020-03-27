package com.example.sleavesystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import static androidx.core.os.LocaleListCompat.create;

public class studentLogin extends Activity {
    EditText e1,e2;
    Button b1;
    Database db1;
    SQLiteDatabase db;
    SharedPreferences sp;
    public static String studentid;
    SharedPreferences.Editor editor;
    public static final String preferences="";
    Cursor cursor;
    CursorAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText7);
        b1=(Button)findViewById(R.id.button7);
        db1=new Database(getApplicationContext(),"LeaveSystem",null,7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = db1.getReadableDatabase();
                String e11=e1.getText().toString();
                String e12=e2.getText().toString();

                if (e11.equals(" ") || e12.equals(" ")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(studentLogin.this).create();
                    alertDialog.setTitle("ALERT!");
                    alertDialog.setMessage("Fill All Fields");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();

                } else
                    {
                        sp=getSharedPreferences(preferences, Context.MODE_MULTI_PROCESS);
                         editor=sp.edit();

                        cursor = db.rawQuery("select PNR,Password from students where PNR=" + e1.getText().toString() + " or Password=" + e2.getText().toString() + " ", null);
                        int count = cursor.getCount();
                        if (count > 0) {
                            editor.putInt("studentid", Integer.parseInt(e1.getText().toString()));
                            editor.commit();
                            Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), StudentProfile.class);
                            startActivity(i);
                        } else
                            Toast.makeText(getApplicationContext(), "unsuccesful" + e2.getText() + "", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
