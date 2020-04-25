package Exercise_17._04;
/*********************************************************************************
 * (Convert a text file into UTF) Write a program that reads lines of characters  *
 * from a text file and writes each line as a UTF-8 string into a binary file.    *
 * Display the sizes of the text file and the binary file. Use the following      *
 * command to run the program:                                                    *
 *********************************************************************************/
import java.io.*;

public class ConvertTextFileIntoUTF {
    public static void main(String[] args) throws IOException { // Github

        // Check java usage
        if(args.length != 2){
            System.out.println("Usage: java ConvertTextFileIntoUTF SourceFile TargetFile");
            System.exit( 1 );
        }

        try (
                BufferedReader input = new BufferedReader( new FileReader( args[0] ) );
                DataOutputStream output = new DataOutputStream( new FileOutputStream( args[1] ) );
        ) {
            String line;
            while ((line = input.readLine()) != null)
                output.writeUTF( line );
        }

        try (
                InputStream input1 = new FileInputStream( args[0] );
                InputStream input2 = new FileInputStream( args[1] );

        ) {
            System.out.println(args[0] + "'s size is " + input1.available() + " bytes");
            System.out.println(args[1] + "'s size is " + input2.available() + " bytes");
        }
    }
}
