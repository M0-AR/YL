package Exercise_11._05;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> students;

    public Course(String courseName) {
        this.courseName = courseName;
        students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String[] getStudents() {
        String[] temp = new String[students.size()];
        return students.toArray(temp);
    }

    public void addStudent(String student) {
        students.add(student);
    }

    public void dropStudent(String student) {
        students.remove(student);
    }

    private void dropStudent(int index) {
        students.remove(index);
    }

    public int getNumberOfStudents() {
        return students.size();
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
    }
}
