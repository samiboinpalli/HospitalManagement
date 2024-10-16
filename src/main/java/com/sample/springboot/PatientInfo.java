package com.sample.springboot;

public class PatientInfo {
    public int id;
    public String name;
    public int age;
    public String allergies;

    public String toString() {
        return id + "||" + name + "||" + age + "||" + allergies;
    }
}