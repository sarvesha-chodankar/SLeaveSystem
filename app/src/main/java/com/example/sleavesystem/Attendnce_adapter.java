package com.example.sleavesystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.ContentValues;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.example.sleavesystem.Database;
import com.example.sleavesystem.students_data;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Attendnce_adapter extends BaseAdapter {
    Database db1;
    SQLiteDatabase db;
    android.database.Cursor cursor;
    Context context;
    EditText e1, e2;
    private ArrayList<students_data> arrayList;
    TextView PNR,name,attendance;
    Button b2;

    public Attendnce_adapter(Context applicationContext, ArrayList<students_data> arrayList) {
        this.context = applicationContext;
        this.arrayList = arrayList;
        db1 = new Database(applicationContext, "LeaveSystem", null, 6);

    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.attendance_details, null);
            PNR = (TextView) convertView.findViewById(R.id.textView17);
            name = (TextView) convertView.findViewById(R.id.textView22);
            attendance = (TextView) convertView.findViewById(R.id.textView23);

            PNR.setText(" " + arrayList.get(position).getPNR());
            name.setText(String.valueOf(arrayList.get(position).getName()));
            attendance.setText(String.valueOf(arrayList.get(position).getAttendance()));

             b2 = (Button) convertView.findViewById(R.id.button5);


            e1 =  (EditText)convertView.findViewById(R.id.editText6);
            e2 =(EditText) convertView.findViewById(R.id.editText5);

            e1.setText(String.valueOf(arrayList.get(position).getEditText1()));
            e2.setText(String.valueOf(arrayList.get(position).getEditText2()));

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db = db1.getWritableDatabase();
                    int id = arrayList.get(position).getPNR();
                    if(arrayList.get(position).getAllowed()!=" ") {

                        ContentValues cv = new ContentValues();

                        double att_percantge;

                        int a=arrayList.get(position).getEditText2();
                        int b=arrayList.get(position).getEditText1();


                        cursor = db.rawQuery("select no_of_leaves from leaves_info where PNR=" +arrayList.get(position).getPNR()  + " and allowed='yes' ", null);
                        //c=cursor.getInt(1)
                        if(cursor.moveToFirst())
                            att_percantge =a/(b - cursor.getInt(0));
                        else
                             att_percantge =a/b;

                        cv.put("Attendance_count", e2.getText().toString());
                        cv.put("total_attendance", e1.getText().toString());
                        cv.put("A_percentage", att_percantge);

                        long rowupdated = db.update("students", cv, "PNR=" + id, null);
                        if (rowupdated != -1)
                            Toast.makeText(parent.getContext(), "view clicked: " + id, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
            return convertView;
        }
}