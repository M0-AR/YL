package Exercise_10._09;

public class Course {
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        students[numberOfStudents] = student;
        numberOfStudents++;
    }

    public String[] getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        String[] newStudents = new String[100];

        for (int i = 0, j = 0; i < numberOfStudents; i++) {
            if (!students[i].equalsIgnoreCase(student)) {
                newStudents[j++] = students[i];
            }
        }

        students = newStudents;
        numberOfStudents--;
    }

}
