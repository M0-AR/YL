package Exercise_05._34;

import java.util.Scanner;

public class ScissorRockPaper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int user, random, computerCounter = 0, userCounter = 0;
        boolean win = false;
        do {
            // Prompt the user to enter 0, 1, or 2
            System.out.println("scissor (0), rock (1), paper (2):");
            user = input.nextInt();

            // Generate a random integer 0, 1 or 2
            random = (int)(Math.random() * 3);
            System.out.println(random);



            System.out.print("The computer is ");
            switch (random)
            {
                case 0: System.out.print("scissor. "); break;
                case 1: System.out.print("rock. "); break;
                case 2: System.out.print("paper. ");
            }

            System.out.print("You are ");
            switch (user)
            {
                case 0: System.out.print("scissor. "); break;
                case 1: System.out.print("rock. "); break;
                case 2: System.out.print("paper. ");
            }

            if (random == user)
                System.out.println("It is a draw");
            else
            {
                 win =  (user == 0 && random == 2)
                        || (user == 1 && random == 0)
                        || (user == 2 && random == 1);
                if (win){
                    System.out.println("You won");
                    userCounter ++;
                }
                else{
                    System.out.println("You loses");
                    computerCounter++;
                }
            }
        }while (userCounter != 2 && computerCounter != 2);
        System.out.println("Winnner counter for user "+userCounter);
        System.out.println("Winnner counter for computer "+computerCounter);
    }
}
