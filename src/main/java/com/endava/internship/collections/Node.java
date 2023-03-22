package com.endava.internship.collections;

public class Node {
    Student key;
    Integer value;
    Node left;
    Node right;

    public Node (Student key, Integer value) {
        this.key = key;
        this.value = value;
    }

    void set (Student student, Integer integer) {
        key = student;
        value = integer;
    }
}