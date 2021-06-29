package Exercise_10._09;
/*********************************************************************************
 * (The Course class) Revise the Course class as follows:                         *
 *                                                                                *
 * ■ The array size is fixed in Listing 10.6. Improve it to automatically         *
 *   increase the array size by creating a new larger array and copying the       *
 *   contents of the current array to it.                                         *
 * ■ Implement the dropStudent method.                                            *
 * ■ Add a new method named clear() that removes all students from the            *
 *   course.                                                                      *
 *                                                                                *
 * Write a test program that creates a course, adds three students, removes one,  *
 * and displays the students in the course.                                       *
 *********************************************************************************/
public class TestCourse {
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
        for (int i = 0; i < dataStructures.getNumberOfStudents(); i++) {
            System.out.print(students[i] +
                    ((i != dataStructures.getNumberOfStudents() - 1) ? ", ": ""));
        }
        System.out.println();
    }
}
