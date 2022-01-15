package Exercise_11._05;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // Create a course
        Course dataStructures = new Course("Data Structures");

        // Add three students
        dataStructures.addStudent("Peter Jones");
        dataStructures.addStudent("Kim Smith");
        dataStructures.addStudent("Anne Kennedy");

        // Remove one student
        dataStructures.dropStudent("Peter Jones");

        // Displays students in the course
        System.out.println("\nThe students in the course" +
                dataStructures.getCourseName() + ":");

        ArrayList<String> students = dataStructures.getStudents();
        for (int i = 0; i < dataStructures.getNumberOfStudents(); i++) {
            System.out.print(students.get(i) +
                    ((i != dataStructures.getNumberOfStudents() - 1) ? ", ": ""));
        }

        System.out.println();
    }
}
