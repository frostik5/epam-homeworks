package se02.task05;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private int studentID;
    private Set<Group> groups;

    public Student(int studentID) {
        this.studentID = studentID;
        groups = new HashSet<>(3);
    }

    public void setGroup(Group group) {
        if (!group.isFull()) {
            groups.add(group);
        }
    }

    public int getStudentID() {
        return studentID;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return studentID == student.studentID;
    }

    @Override
    public int hashCode() {
        return studentID;
    }
}
