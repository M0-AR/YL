package Exercise_17._02;
/*********************************************************************************
 * (Create a binary data file) Write a program to create a file named             *
 * Exercise17_02.dat if it does not exist. Append new data to it if it already    *
 * exists. Write 100 integers created randomly into the file using binary I/O.    *
 *********************************************************************************/
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBinaryDataFile {
    public static void main(String[] args) throws IOException {
        try (
                // True mean to add the new data to the old
                FileOutputStream output =  new FileOutputStream( "Exercise17.02.dat" , true);
        ) {
            // 100 integers created randomly into the file
            for (int i = 0; i < 100 ; i++)
                output.write( (int)(Math.random() * 100000) );

        }

        System.out.println("Done");
    }
}
