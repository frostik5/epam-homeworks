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
        if (this.groups.size() <= 5) {
            groups.add(group);
        }
    }

    public int getStudentID() {
        return studentID;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
