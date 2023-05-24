package com.example.cowlogs;

public class cowLogs {
    private int cow;
    private String ID;
    private String weight;
    private String age;
    private String condition;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;

    public void setAll(int cow, String condition, int day, int month, int year, int hour, int minute, int second, String ID, String weight, String age){
        this.cow = cow;
        this.condition = condition;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.ID = ID;
        this.weight = weight;
        this.age = age;
    }
    public String getAll(){
        return (condition
                + " " + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second
                + " " + ID + " " + weight + " " + age);
    }

    public void setCow(int cow){
        this.cow = cow;
    }
    public int getCow(){
        return cow;
    }

    public void setID(String ID){
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }

    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getWeight(){
        return weight;
    }

    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return age;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }
    public String getCondition(){
        return condition;
    }

    public void setDay(int day){
        this.day = day;
    }
    public int getDay(){
        return day;
    }

    public void setMonth(int month){
        this.month = month;
    }
    public int getMonth(){
        return month;
    }

    public void setYear(int year){
        this.year = year;
    }
    public int getYear(){
        return year;
    }

    public void setHour(int hour){
        this.hour = hour;
    }
    public int getHour(){
        return hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }
    public int getMinute(){
        return minute;
    }

    public void setSecond(int second){
        this.second = second;
    }
    public int getSecond(){
        return second;
    }

    @Override                   //converts data to string for output
    public String toString()
    {
        return getClass().getName()+ "" + cow + " " + condition
                + " " + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second
                + " " + ID + " " + weight + " " + age;
    }
}
