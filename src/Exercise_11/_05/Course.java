package Exercise_11._05;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> students = new ArrayList<>();
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void addStudent(String student) {
        students.add(student);
        numberOfStudents++;
    }

    public void dropStudent(String student) {
        int index = findStudent(student);
        if (index != -1) {
            dropStudent(index);
        } else {
            System.out.println(student +
                    " is not in the course: " + courseName);
        }
    }

    private void dropStudent(int index) {
        students.remove(index);
        numberOfStudents--;
    }

    private int findStudent(String student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equalsIgnoreCase(student))
                return i;
        }
        return -1;
    }

    public void clear() {
        students = new ArrayList<>();
        numberOfStudents = 0;
    }
}
