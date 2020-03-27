package com.example.sleavesystem;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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
import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {
    Database db1;
    SQLiteDatabase db;
    android.database.Cursor cursor;
    Context context;
    private ArrayList<students_data> arrayList;
    private TextView PNR;
    private TextView reason;
    private TextView days;
    private TextView date_to;
    private TextView date_from;
    private TextView allowed;
    Button b1;

    public CustomAdapter(Context applicationContext, ArrayList<students_data> arrayList) {
        this.context = applicationContext;
        this.arrayList = arrayList;
        db1=new Database(applicationContext,"LeaveSystem",null,7);

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
            convertView = layoutInflater.inflate(R.layout.leave_details,null);
            PNR = (TextView) convertView.findViewById(R.id.textView16);
            reason = (TextView)convertView.findViewById(R.id.textView21);
            days = (TextView)convertView.findViewById(R.id.textView20);
            date_to = (TextView)convertView.findViewById(R.id.textView18);
            date_from =(TextView) convertView.findViewById(R.id.textView19);
            allowed =(TextView) convertView.findViewById(R.id.textView32);

           PNR.setText(" " + arrayList.get(position).getPNR());
            reason.setText(String.valueOf(arrayList.get(position).getReason()));
            days.setText(String.valueOf(arrayList.get(position).getDays()));
            date_to.setText(String.valueOf(arrayList.get(position).getDate_to()));
            date_from.setText(String.valueOf(arrayList.get(position).getDate_from()));
            allowed.setText(String.valueOf(arrayList.get(position).getAllowed()));


             b1=(Button)convertView.findViewById(R.id.button10);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db = db1.getWritableDatabase();

                    int id= arrayList.get(position).getPNR();

                    ContentValues cv = new ContentValues();
                    cv.put("allowed","yes");
                    long rowupdated =db.update("Leaves_info", cv, "PNR="+id, null);
                    if(rowupdated!=-1)
                    {
                        Toast.makeText(parent.getContext(), "view clicked: " + id, Toast.LENGTH_SHORT).show();
                        //Notification.Builder nm=new Notification.Builder(this);
                        //nm.setContentTitle("Leave Request");
                        //nm.setContentText("ok");
                    }


                }
            });

        }

        return convertView;
    }
}
