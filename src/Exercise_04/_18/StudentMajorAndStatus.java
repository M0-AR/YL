package Exercise_04._18;
/*
(Student major and status) Write a program that prompts the user to enter two
characters and displays the major and status represented in the characters. The first
character indicates the major and the second is number character 1, 2, 3, 4, which
indicates whether a student is a freshman, sophomore, junior, or senior. Suppose
the following chracters are used to denote the majors:
M: Mathematics
C: Computer Science
I: Information Technology
*/
import java.util.Scanner;
public class StudentMajorAndStatus { // done
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        // Prompt the user to enter two characters:
        System.out.print("Enter two characters: ");
        String ch = scanner.nextLine();

        String majors = "";
        switch (ch.charAt( 0 )){
            case 'M': majors = "Mathmatics";break;
            case 'C': majors = "Computer Science";break;
            case 'I': majors = "Information Technology";break;
            default:
                System.out.print("Invalid input: Wrong major code");
                System.exit( 1 );
        }

        int number = 0;
        switch (ch.charAt( 1 )){
            case '1': majors += " freshman";break;
            case '2': majors += " sophomore";break;
            case '3': majors += " junior";break;
            case '4': majors += " senior";break;
            default:
                System.out.print("Invalid input: Wrong major code");
                System.exit( 2 );
        }

        System.out.println(majors);
    }
}
