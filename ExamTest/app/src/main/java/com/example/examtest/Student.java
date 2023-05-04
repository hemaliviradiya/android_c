package com.example.examtest;

public class Student {
    int rollno;
    String name,gender,stream;

    public Student(){}

    public Student(int rollno, String name, String gender, String stream) {
        this.rollno = rollno;
        this.name = name;
        this.gender = gender;
        this.stream = stream;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
