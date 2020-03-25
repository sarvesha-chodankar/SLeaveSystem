package com.example.sleavesystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by LENOVO on 26-02-2020.
 */

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String sql="create table students(_id INTEGER PRIMARY KEY AUTOINCREMENT,PNR INTEGER,Name CHAR,Attendance_count INTEGER,total_attendance INTEGER,A_percentage DECIMAL,Password INTEGER,course_code INTEGER,FOREIGN KEY(course_code) REFERENCES course(course_code))";
        String sql1="create table subject(_id INTEGER PRIMARY KEY ,subject_name CHAR,faculty_id INTEGER,course_code INTEGER,FOREIGN KEY(faculty_id) REFERENCES faculty(_id),FOREIGN KEY(course_code) REFERENCES course(course_code))";
        String sql2="create table course(_id INTEGER PRIMARY KEY ,course_name CHAR)";
        String sql3="create table faculty(_id INTEGER PRIMARY KEY ,email char,Name CHAR,designation char,Password INTEGER)";
        String sql4="create table leaves_info(_id INTEGER PRIMARY KEY AUTOINCREMENT,date_to char,date_from char,no_of_leaves INTEGER,reason char,allowed char,PNR INTEGER,FOREIGN KEY(PNR) REFERENCES students(PNR))";
        String sql5="create table class_teacher(_id INTEGER PRIMARY KEY AUTOINCREMENT,faculty_id INTEGER,course_code INTEGER,FOREIGN KEY(course_code) REFERENCES course(course_code),FOREIGN KEY(faculty_id) REFERENCES faculty(_id))";

        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql);
        db.execSQL(sql4);
        db.execSQL(sql1);
        db.execSQL(sql5);

        String isql11="insert into course(_id,course_name) values (101,'SYMCA')";
        String isql21="insert into course(_id,course_name) values (102,'FYMCA')";
        String isql131="insert into course(_id,course_name) values (103,'TYMCA')";
        db.execSQL(isql21);
        db.execSQL(isql11);
        db.execSQL(isql131);



        String isql112="insert into faculty(_id,email,Name,designation,Password)values(1,'r@gmail.com','Rekha','Class Teacher',1234)";
        String isqll13="insert into faculty(_id,email,Name,designation,Password)values(2,'s@gmail.com','Sushna','Teacher',1234)";
        String isqll14="insert into faculty(_id,email,Name,designation,Password)values(3,'p@gmail.com','Priyanka ','Class Teacher',1234)";
        db.execSQL(isqll13);
        db.execSQL(isqll14);
        db.execSQL(isql112);

       String isql1="insert into students(_id,PNR,Name,Attendance_count,total_attendance,A_percentage,Password,course_code) values(1,1231,'Shubhum Naik',2,0,50,1234,101)";
        String isql2="insert into students(_id,PNR,Name,Attendance_count,total_attendance,A_percentage,Password,course_code) values(2,1232,'Sneha Vaidya',2,0,50,1234,102)";
        String isql3="insert into students(_id,PNR,Name,Attendance_count,total_attendance,A_percentage,Password,course_code) values(3,1233,'Akash Kumar',2,0,0,1234,103)";
        String isql4="insert into students(_id,PNR,Name,Attendance_count,total_attendance,A_percentage,Password,course_code) values(4,12312,'Akshay Naik',2,0,50,1234,101)";
        String isql5="insert into students(_id,PNR,Name,Attendance_count,total_attendance,A_percentage,Password,course_code) values(5,123123,'Vasant Rao',2,0,0,1234,104)";
        db.execSQL(isql1);
        db.execSQL(isql2);
        db.execSQL(isql3);
        db.execSQL(isql4);
        db.execSQL(isql5);



        String isq01="insert into class_teacher(_id,faculty_id,course_code) values (1,1,101)";
        String isq02="insert into class_teacher(_id,faculty_id,course_code) values (2,2,102)";
        db.execSQL(isq01);
        db.execSQL(isq02);


        String isql01="insert into subject(_id,subject_name,faculty_id,course_code) values(110,'ASP.NET',1,101)";
        String isql02="insert into subject(_id,subject_name,faculty_id,course_code) values(111,'PHP',2,101)";
        String isql03="insert into subject(_id,subject_name,faculty_id,course_code) values(112,'Core Java',3,101)";
        db.execSQL(isql01);
        db.execSQL(isql02);
        db.execSQL(isql03);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dsql1="drop table if exists students";
        String dsql2="drop table if exists subject";
        String dsql3="drop table if exists course";
        String dsql4="drop table if exists leaves_info";
        String dsql5="drop table if exists faculty";
        String dsql6="drop table if exists class_teacher";

       db.execSQL(dsql1);
       db.execSQL(dsql2);
        db.execSQL(dsql3);
        db.execSQL(dsql4);
        db.execSQL(dsql5);
        db.execSQL(dsql1);
        onCreate(db);


    }



}
