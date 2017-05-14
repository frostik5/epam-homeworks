package se02.task05;

import java.util.*;

public class Group {
    public static final int GROUP_CAPACITY = 20;
    public static final int TOTAL_MARKS = 5;
    private Subject subject;
    private int groupID;
    private Map<Student, List<Mark>> studentsAndMarks;

    public Group(Subject subject, int groupID) {
        this.subject = subject;
        this.groupID = groupID;
        studentsAndMarks = new HashMap<>();
    }

    public void addStudent(Student student) {
        Random r = new Random();
        List<Mark> marks = new ArrayList<>(TOTAL_MARKS);
        for (int i = 0; i < TOTAL_MARKS; i++) {
            marks.add(new Mark<>(r.nextInt(5) + 1));
        }
        studentsAndMarks.put(student, marks);
    }

    public boolean isFull() {
        return studentsAndMarks.keySet().size() == GROUP_CAPACITY;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(studentsAndMarks.keySet());
    }

    public Map<Student, List<Mark>> getStudentsAndMarks() {
        return studentsAndMarks;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
