package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Student {
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student (String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName () {
        return name;
    }

    public LocalDate getDateOfBirth () {
        return dateOfBirth;
    }

    public String getDetails () {
        return details;
    }

    public int compareTo (Student student) {
        return Comparator.comparing(Student::getName).thenComparing(Student::getDateOfBirth).compare(this, student);
    }

    @Override
    public boolean equals (Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Student student = (Student) object;
        return name.equals(student.name) && dateOfBirth.equals(student.dateOfBirth);
    }

    @Override
    public int hashCode () {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public String toString () {
        return name + "\n" + dateOfBirth + "\n" + details;
    }

}

