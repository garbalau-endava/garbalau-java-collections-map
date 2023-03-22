package com.endava.internship.collections;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Homework Collections");
        // Get current date
        LocalDate date = LocalDate.now();

        // Testing Student
        Student student = new Student("Nick", date, "Developer");
        System.out.println(student.toString());

        // Testing StudentMap
        StudentMap studentMap = new StudentMap();
        studentMap.put(student, 10);
        System.out.println(studentMap.isEmpty());
        System.out.println(studentMap.size());
        System.out.println(studentMap.values());
        System.out.println(studentMap.keySet());
    }
}
