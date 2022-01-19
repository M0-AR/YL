package Exercise_11._05;

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

        String[] students = dataStructures.getStudents();
        for (int i = 0; i < students.length; i++) {
            System.out.print(students[i] +
                    ((i != students.length - 1) ? ", ": ""));
        }

        System.out.println();
    }
}
