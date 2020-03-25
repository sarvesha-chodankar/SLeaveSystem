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
import android.widget.EditText;
import android.widget.Toast;

import com.example.sleavesystem.R;


public class FacultyLogin extends Activity {
    EditText e1,e2;
    Button b1;
    Database db1;
    SQLiteDatabase db;
    Cursor cursor;
    SharedPreferences.Editor editor;
    public static final String preferences="";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText7);
        b1=(Button)findViewById(R.id.button7);
        db1=new com.example.sleavesystem.Database(getApplicationContext(),"LeaveSystem",null,6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=db1.getReadableDatabase();

                String e11=e1.getText().toString();
                String e12=e2.getText().toString();

                if (e11.equals(" ") || e12.equals(" ")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(FacultyLogin.this).create();
                    alertDialog.setTitle("ALERT!");
                    alertDialog.setMessage("Fill All Fields");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();

                } else
                {
                cursor=db.rawQuery("select * from faculty where _id="+e1.getText()+" or Password= "+e2.getText().toString()+"  ",null);

                int count=cursor.getCount();
                if (count>0)
                {
                    sp=getSharedPreferences(preferences, Context.MODE_PRIVATE);
                    editor=sp.edit();
                    String facultyid="";
                    editor.putInt("facultyid",Integer.parseInt(e11));
                    editor.apply();
                    Intent i=new Intent(getApplicationContext(), com.example.sleavesystem.FacultyProfile1.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"unsuccesful"+e1.getText()+"",Toast.LENGTH_LONG).show();
            }}
        });


    }
}
