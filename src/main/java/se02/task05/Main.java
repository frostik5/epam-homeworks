package se02.task05;

import java.util.*;

/**
 * Разработайте приложение, позволяющее формировать группы студентов по разным дисциплинам.
 * Состав групп может быть разным. Каждая дисциплина в отдельности определяет, целыми или вещественными будут оценки по нет.
 * Необходимо найти группы, в которые входит студент X, и сравнить его оценки. Для огранизации перечня дисциплин можно использовать перечисление.
 */
public class Main {
    private static List<Group> groups;
    private static Set<Student> students;

    /**
     *  Won't work for groupsPerSubject > 1 for now. So each subject has only 1 group with it's unique students.
     */
    public static void main(String[] args) {
        createGroups(100);
    }

    private static void createGroups(int totalStudents) {
        groups = new ArrayList<>(Subject.values().length); // 1 group per Subject
        for (int i = 0; i < (Subject.values().length); i++) {
            groups.add(new Group(Subject.values()[i], i+1));
        }

        createStudents(totalStudents);
        addStudentsToGroups();

        for (Group group: groups) {
            System.out.println("---ID " + group.getGroupID() + ": " + group.getSubject() + "---");
            for (Student student: group.getStudentsAndMarks().keySet()) {
                System.out.println("Студент: " + student.getStudentID() + ", список оценок:");
                Iterator<Map.Entry<Student, List<Mark>>> entries = group.getStudentsAndMarks().entrySet().iterator();
                while (entries.hasNext()) {

                }
                for (List mark: group.getStudentsAndMarks().values()) {
                    //System.out.println(mark.get);
                }
                System.out.println();

                //for (int i = 0; i < group.getStudentsAndMarks().values().size(); i++) {
                //    System.out.println(i + ") " + group.getStudentsAndMarks().values();
                //}
            }
        }
    }

    private static void createStudents(int totalStudents) {
        students = new HashSet<>(totalStudents);
        Random r = new Random();
        for (int i = 0; i < totalStudents; i++) {
            students.add(new Student(r.nextInt(totalStudents) + 1));
        }
    }

    private static void addStudentsToGroups() {
        for (Group group: groups) {
            for (Student student: students) {
                if (!group.isFull()) {
                    group.addStudent(student);
                }
            }
        }
    }
}

