package com.endava.internship.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StudentMap implements Map<Student, Integer> {

    static class Node {
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

    private int size;
    private Node parent;
    private Node found;

    @Override
    public int size () {
        return size;
    }

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public boolean containsKey (Object object) {
        return foundNode(parent, (Student) object) != null;
    }

    @Override
    public boolean containsValue (Object object) {
        return findNodeValue(parent, (Integer) object);
    }

    @Override
    public Integer get (Object object) {
        Student student = (Student) object;
        if (containsKey(student)) {
            Node node = foundNode(parent, student);
            found = null;
            if (node != null) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Integer put (Student student, Integer integer) {
        if (containsKey(student)) {
            Node node = foundNode(parent, student);
            found = null;
            if (node != null) {
                Integer oldValue = node.value;
                node.set(student, integer);
                return oldValue;
            } else {
                return null;
            }
        } else {
            parent = insert(parent, student, integer);
            size++;
            return null;
        }
    }

    @Override
    public Integer remove (Object object) {
        Student student = (Student) object;
        if (containsKey(student)) {
            Node node = foundNode(parent, student);
            found = null;
            if (node != null) {
                parent = deleteNode(parent, student);
                size--;
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void putAll (Map<? extends Student, ? extends Integer> map) {
        for (Student s : map.keySet()) {
            put(s, map.get(s));
        }
    }

    @Override
    public void clear () {
        size = 0;
        parent = null;
    }

    @Override
    public Set<Student> keySet () {
        Set<Student> students = new LinkedHashSet<>();
        getKey(parent, students);
        return students;
    }

    @Override
    public Collection<Integer> values () {
        Collection<Integer> values = new ArrayList<>();
        getValue(parent, values);
        return values;
    }

    @Override
    public Set<Entry<Student, Integer>> entrySet () {
        // Ignore this for homework
        throw new UnsupportedOperationException();
    }

    private void getKey (Node parent, Set<Student> students) {
        if (parent != null) {
            getKey(parent.left, students);
            students.add(parent.key);
            getKey(parent.right, students);
        }
    }

    private void getValue (Node parent, Collection<Integer> values) {
        if (parent != null) {
            getValue(parent.left, values);
            values.add(parent.value);
            getValue(parent.right, values);
        }
    }

    private Node foundNode (Node parent, Student student) {
        if (parent == null) {
            return null;
        }
        if (student.compareTo(parent.key) == 0
                || foundNode(parent.left, student) != null
                || foundNode(parent.right, student) != null) {
            return parent;
        }
        return null;
    }

    private boolean findNodeValue (Node parent, Integer value) {
        if (parent == null) {
            return false;
        } else if (Objects.equals(value, parent.value)) {
            return true;
        } else {
            return findNodeValue(parent.left, value) || findNodeValue(parent.right, value);
        }
    }

    private Node insert (Node parent, Student student, Integer value) {
        if (parent == null) {
            parent = new Node(student, value);
        }
        int result = student.compareTo(parent.key);
        if (result < 0) {
            parent.left = insert(parent.left, student, value);
        } else if (result > 0) {
            parent.right = insert(parent.right, student, value);
        }
        return parent;
    }

    private Node deleteNode (Node parent, Student student) {
        if (parent == null) {
            return null;
        }
        int result = student.compareTo(parent.key);
        if (result < 0) {
            parent.left = deleteNode(parent.left, student);
        } else if (result > 0) {
            parent.right = deleteNode(parent.right, student);
        } else {
            if (parent.left == null) {
                return parent.right;
            } else if (parent.right == null) {
                return parent.left;
            }
            parent.key = parent.right.key;
            while (parent.right.left != null) {
                parent.key = parent.right.left.key;
            }
            parent.right = deleteNode(parent.right, parent.key);
        }
        return parent;
    }
}