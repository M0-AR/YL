package Exercise_06._38;

public class GenerateRandomCharacters { // Yl and github
    public static void main(String[] args) {
        RandomCharacter();
    }

    public static void RandomCharacter(){

        for (int i = 1; i <= 100 ; i++) {

            // To print random uppercase letters
            System.out.print((char)(Math.random() * 26 + 'A') + " ");

            // To handle every 10 letters on one line
            if (i % 10 == 0)
                System.out.println();

        }


        for (int i = 1; i <= 100 ; i++) {

            // To print random single digit
            System.out.print((int)(Math.random() * 10) + " ");

            // To handle every 10 letters on one line
            if (i % 10 == 0)
                System.out.println();

        }
    }
}
