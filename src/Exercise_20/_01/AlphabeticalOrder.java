package Exercise_20._01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlphabeticalOrder {
    public static void main(String[] args) throws FileNotFoundException {
        // Check number of arguments passed
        if(args.length != 1) {
            System.out.println("Usage: java Exercise_20._01 TextFile");
            System.exit( 1 );
        }

        // Check if file exists
        File textFile = new File( args[0] );
        if (!textFile.exists()) {
            System.out.println("The file " + args[0] + " does not exist.");
            System.exit( 2 );
        }

        // Create a list of string
        List<String> list = new ArrayList<>();

        try (
                // Create input file
                Scanner input = new Scanner(textFile);
        ) {
                while (input.hasNext()) {
                    String[] arrayOfWords = input.nextLine().split( " " );
                    for (int i = 0; i < arrayOfWords.length; i++) {
                        if (arrayOfWords[i].length() > 0 &&
                                Character.isLetter( arrayOfWords[i].charAt(0))) {
                                list.add( arrayOfWords[i] );
                        }
                    }
                }
        }

        // Sort the list in ascending alphabetical order
        Collections.sort( list );

        // Display the list
        System.out.println( list );
    }
}
