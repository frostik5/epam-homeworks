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
    private static int GROUPS_PER_SUBJECT = 2;
    private static final int TOTAL_SUBJECTS = Subject.values().length;
    private static int TOTAL_GROUPS = TOTAL_SUBJECTS * GROUPS_PER_SUBJECT;

    public static void main(String[] args) {
        createGroups(100);
        printAllStudentsInGroups();
        //printAllStudentMarks();
        //findStudent1InGroups();
    }

    private static void createGroups(int totalStudents) {
        groups = new ArrayList<>(TOTAL_GROUPS);
        for (int i = 0, j = 0; i < TOTAL_GROUPS; i++) {
            if (j != 0 && j % (TOTAL_SUBJECTS - 1) == 0) {
                groups.add(new Group(Subject.values()[j], i + 1));
                j = 0;
            }
            else {
                groups.add(new Group(Subject.values()[j], i + 1));
                j++;
            }
        }

        createStudents(totalStudents);
    }

    private static void createStudents(int totalStudents) {
        Random r = new Random();
        students = new ArrayList<>(totalStudents);
        for (int i = 0; i < totalStudents; i++) {
            students.add(new Student(i + 1));
        }

        for (int i = 0; i < TOTAL_GROUPS; i++) {
            // Если у каждого предмета больше 1 группы
            if (GROUPS_PER_SUBJECT > 1) {
                int someStudent;

                // Если это ещё одна группа по тому же предмету (Например, если GROUPS_PER_SUBJECT = 3, то ID такких групп будут: 1, 6, 11)
                if (i != 0 && i >= TOTAL_SUBJECTS - 1) {

                    // Заполняем групппу студентами
                    for (int j = 0; j < r.nextInt(Group.GROUP_CAPACITY) + 10; j++) {
                        someStudent = r.nextInt(totalStudents); // Берём случайный номер студента (для списка students)
                        int studentsInAGroup = groups.get(i - (TOTAL_SUBJECTS - 1)).getStudents().size();
                        for (int k = 0; k < studentsInAGroup; k++) {
                            // Если мы нашли ID такого же студента, то выходим из цикла
                            if (k < (studentsInAGroup - 1) && studentFoundInAnotherGroup(i, someStudent, k)) {
                                break;
                            // Если мы прошлись по всем студентам другой группы и последний студент не совпал,
                            } else if (k == (studentsInAGroup - 1) && groups.get(i - (TOTAL_SUBJECTS - 1)).getStudents().get(k).getStudentID() != students.get(someStudent).getStudentID()) {
                                groups.get(i).addStudent(students.get(someStudent)); // добавляем его
                                break;  // и выходим из цикла
                            }
                        }
                    }

                // Если у каждого предмета всего 1 группа
                } else {
                    // Просто добавляем случайных студентов из списка
                    for (int j = 0; j < r.nextInt(Group.GROUP_CAPACITY) + 10; j++) {
                        groups.get(i).addStudent(students.get(r.nextInt(totalStudents)));
                    }
                }

            // Если у каждого предмета всего 1 группа
            } else {
                //просто добавляем случайных студентов из списка
                for (int j = 0; j < r.nextInt(Group.GROUP_CAPACITY) + 10; j++) {
                    groups.get(i).addStudent(students.get(r.nextInt(totalStudents)));
                }
            }
        }
    }

    private static boolean studentFoundInAnotherGroup(int i, int someStudent, int k) {
        int studID1 = groups.get(i - (TOTAL_SUBJECTS - 1)).getStudents().get(k).getStudentID();
        int studID2 = students.get(someStudent).getStudentID();
        return groups.get(i - (TOTAL_SUBJECTS - 1)).getStudents().get(k).getStudentID() == students.get(someStudent).getStudentID();
    }

    private static void printAllStudentsInGroups() {
        for (Group group: groups) {
            System.out.println("---" + group.getSubject() + ", группа №" + group.getGroupID() + "---");
            for (Map.Entry<Student, List<Mark>> studentsAndMarks : group.getStudentsAndMarks().entrySet()) {
                System.out.println("ID студента: " + studentsAndMarks.getKey().getStudentID());
            }
        }
    }

    private static void printAllStudentMarks() {
        for (Group group: groups) {
            System.out.println("---" + group.getSubject() + ", группа №" + group.getGroupID() + "---");
            for (Map.Entry<Student, List<Mark>> studentsAndMarks: group.getStudentsAndMarks().entrySet()) {
                System.out.println("ID студента: " + studentsAndMarks.getKey().getStudentID()/* + ", список оценок:"*/);
                for (int i = 0; i < Group.TOTAL_MARKS; i++) {
                    if (group.getSubject().isIntMark()) {
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

