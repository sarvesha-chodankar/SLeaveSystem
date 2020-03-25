package com.example.sleavesystem;

public class students_data {
    private int PNR;
    private String name;
    private String reason;
    int date_to;
    int date_from,days;
    String allowed;
    int attendance;
    private int editText1,editText2;


    public students_data(int PNR, int date_to, int date_from,int days, String reason,String allowed)
    {
        this.PNR = PNR;
        this.name = name;
        this.date_from=date_from;
        this.date_to=date_to;
        this.reason = reason;
        this.days=days;
        this.allowed=allowed;
    }
    public students_data(int PNR, String name, int attendance,int setEditText1,int setEditText2) {
        this.PNR = PNR;
        this.name = name;
        this.attendance = attendance;
        this.editText1=setEditText1;
        this.editText2=setEditText2;
    }
    public int getPNR() {
        return PNR;
    }
    public void setPNR(int num) {
        this.PNR = num;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int num) {
        this.PNR = days;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String mobileNumber) {
        this.reason = reason;
    }
    public int getDate_to() {
        return date_to;
    }
    public void setDate_to(int num) {
        this.date_to = date_to;
    } public int getDate_from() {
        return date_from;
    }
    public void setDate_from(int num) {
        this.date_from = date_from;
    }
    public void  setAllowed(String allowed){ this.allowed=allowed;}
    public String getAllowed(){return allowed;}
    public int getAttendance(){return attendance;}
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
    public int getEditText1() {
        return editText1;
    }
    public void setEditText1(int editTextValue) {
        this.editText1 = editTextValue;
    }
    public int getEditText2() {
        return editText2;
    }
    public void setEditText2(int editTextValue) {
        this.editText2 = editTextValue;
    }
}
