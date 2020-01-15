package Exercise_04._18;

import java.util.Scanner;// problem with T3 test

public class StudentMajorAndStatus { // g and y
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
                System.out.print("Invalid input");
        }

        int number = 0;
        switch (ch.charAt( 1 )){
            case '1': majors += " freshman";break;
            case '2': majors += " sophomore";break;
            case '3': majors += " junior";break;
            case '4': majors += " senior";break;
        }

        System.out.println(majors);
    }
}
