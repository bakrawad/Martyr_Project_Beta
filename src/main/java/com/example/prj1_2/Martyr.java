package com.example.prj1_2;

import java.util.Date;

public class Martyr implements Comparable<Martyr> {
    private String name;
    private int age;
    private String location;
    private Date date;
    private char gender;

    public Martyr(String name, int age, String location, Date date, char gender) {
        this.name = name;
        setAge(age);
        this.location = location;
        setDate(date);
        setGender(gender);
    }

    public Martyr() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age>0&&age<100){
            this.age = age;
        }else
            this.age=0;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender=='f'||gender=='F'||gender=='m'||gender=='M'){
            this.gender = gender;
        }else {
            this.gender='M';
        }

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int compareTo(Martyr o) {
        return date.compareTo(o.date);
    }

    @Override
    public String toString() {
        return
                ","+ name + ","+age + "," + location + "," + date + "," + gender+"\n"
                ;
    }
}
