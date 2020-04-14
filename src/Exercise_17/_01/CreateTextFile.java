package Exercise_17._01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateTextFile { // github and con if the file already have information how to store the number after information
    public static void main(String[] args) throws IOException {

        File file = new File( "Exercise17_01.txt" );
        // if the file already exist will do nothing
        file.createNewFile();

        // To write to a file
        // Write try in this way will close the file automatically
        try (PrintWriter printWriter = new PrintWriter( file )){
            // Write 100 integers randomly to a file
            for (int i = 0; i < 100 ; i++){
                int random = (int)(Math.random() * 100 + 1);
                printWriter.print(random);
                printWriter.print(" ");
            }
        }






    }
}
