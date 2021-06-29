package Exercise_10._09;

public class Course {
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
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

    public void addStudent(String student) {
        if (students.length == numberOfStudents) {
            String[] s = new String[students.length * 2];

            System.arraycopy(students, 0, s, 0, students.length);
            students = s;
        }

        students[numberOfStudents] = student;
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
        String[] s = new String[students.length - 1];

        for (int i = 0, j = 0; i < numberOfStudents; i++) {
            if (i != index) {
                s[j++] = students[i];
            }
        }

        students = s;
        numberOfStudents--;
    }

    private int findStudent(String student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(student))
                return i;
        }
        return -1;
    }

    public void clear() {
        students = new String[100];
        numberOfStudents = 0;
    }

}
