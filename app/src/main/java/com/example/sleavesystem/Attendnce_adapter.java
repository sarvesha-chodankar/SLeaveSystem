package com.example.sleavesystem;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.content.ContentValues;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.app.ActivityCompat;

import com.example.sleavesystem.Database;
import com.example.sleavesystem.students_data;

import java.math.BigDecimal;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;


public class Attendnce_adapter extends BaseAdapter {
    Database db1;
    SQLiteDatabase db,rdb;
    android.database.Cursor cursor;
    Context context;
    EditText e1, e2;
    private ArrayList<students_data> arrayList;
    TextView PNR,name,attendance;
    Button b2;

    public Attendnce_adapter(Context applicationContext, ArrayList<students_data> arrayList) {
        this.context = applicationContext;
        this.arrayList = arrayList;
        db1 = new Database(applicationContext, "LeaveSystem", null, 7);

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


            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //ActivityCompat.requestPermissions((Activity) context.getApplicationContext(),new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
                    db = db1.getWritableDatabase();
                    rdb=db1.getReadableDatabase();
                    int id = arrayList.get(position).getPNR();
                    String mobile = null;
                    cursor=rdb.rawQuery("select Mobile_no from students where PNR="+id,null);
                    if(cursor.moveToFirst())
                        mobile=cursor.getString(0);

                    //Intent intent=new Intent(context.getApplicationContext(),Attendnce_adapter.class);
                    //PendingIntent pi= PendingIntent.getActivity(context.getApplicationContext(), 0, intent,0);

                    //Get the SmsManager instance and call the sendTextMessage method to send message
                    try {
                        ArrayList<String> data=new ArrayList<String>();
                        data.add("Dear Student, this is to inform you that your attendance is below 75%. ");
                        data.add("Any student with less than 75% attendance will not be allowed to appear for the exams, hence attend lectures");
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendMultipartTextMessage(mobile, null, data, null, null);
                        Toast.makeText(context.getApplicationContext(), "Message Sent successfully to !" + mobile, Toast.LENGTH_LONG).show();

                    }
                    catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), "Message not Sent  !" + mobile, Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

            });
        }
            return convertView;
        }
}