package se02.task05;

import java.util.*;

/**
 * Разработайте приложение, позволяющее формировать группы студентов по разным дисциплинам.
 * Состав групп может быть разным. Каждая дисциплина в отдельности определяет, целыми или вещественными будут оценки по нет.
 * Необходимо найти группы, в которые входит студент X, и сравнить его оценки. Для огранизации перечня дисциплин можно использовать перечисление.
 */
public class Main {
    private static List<Group> groups;
    private static List<Student> students;

    /**
     *  Won't work for groupsPerSubject > 1 for now. So each subject has only 1 group with its unique students.
     */
    public static void main(String[] args) {
        createGroups(100);
        printAllStudentsInGroups();
        //printAllStudentMarks();
        findStudent1InGroups();
    }

    private static void createGroups(int totalStudents) {
        groups = new ArrayList<>(Subject.values().length); // 1 group per Subject
        for (int i = 0; i < (Subject.values().length); i++) {
            groups.add(new Group(Subject.values()[i], i+1));
        }

        createStudents(totalStudents);
    }

    private static void createStudents(int totalStudents) {
        Random r = new Random();
        students = new ArrayList<>(totalStudents);
        for (int i = 0; i < totalStudents; i++) {
            students.add(new Student(i + 1));
        }

        for (Group group: groups) {
            for (int i = 0; i < r.nextInt(Group.GROUP_CAPACITY) + 10; i++) {
                group.addStudent(students.get(r.nextInt(totalStudents)));
            }
        }
    }

    private static void printAllStudentsInGroups() {
        for (Group group: groups) {
            System.out.println("---ID " + group.getGroupID() + ": " + group.getSubject() + "---");
            for (Map.Entry<Student, List<Mark>> studentsAndMarks : group.getStudentsAndMarks().entrySet()) {
                System.out.println("Студент: " + studentsAndMarks.getKey().getStudentID());
            }
        }
    }

    private static void printAllStudentMarks() {
        for (Group group: groups) {
            System.out.println("---ID " + group.getGroupID() + ": " + group.getSubject() + "---");
            for (Map.Entry<Student, List<Mark>> studentsAndMarks: group.getStudentsAndMarks().entrySet()) {
                System.out.println("Студент: " + studentsAndMarks.getKey().getStudentID()/* + ", список оценок:"*/);
                for (int i = 0; i < Group.TOTAL_MARKS; i++) {
                    if (studentsAndMarks.getValue().get(i).getMarkType() instanceof  Integer) {
                        System.out.println(studentsAndMarks.getValue().get(i).getIntegerMark());
                    } else {
                        System.out.printf("%.2f\n", studentsAndMarks.getValue().get(i).getDoubleMark());
                    }
                }
            }
        }
    }

    private static void findStudent1InGroups() {
        System.out.println("\nСтудент " + groups.get(0).getStudents().get(0).getStudentID() + " входит в следующие группы:");
        for (Group group: groups.get(0).getStudents().get(0).getGroups()) {
            System.out.println(group.getSubject());
        }


    }
}

