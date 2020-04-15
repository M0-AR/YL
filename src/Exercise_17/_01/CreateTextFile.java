package Exercise_17._01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateTextFile { // github
    public static void main(String[] args) throws IOException {

        // To store the information from the file if the file already exist
        ArrayList<String> store =  new ArrayList<>();


        File file = new File( "Exercise17_01.txt" );
        if (file.exists()){
            Scanner input = new Scanner( file );
            while (input.hasNext()){
                store.add( input.next() );
            }
        }else {
            // if the file already exist will do nothing
            file.createNewFile();
        }




        // To write to a file
        // Write try in this way will close the file automatically
        try (PrintWriter printWriter = new PrintWriter( file )){
            // To write the old information to the file
            if (!store.isEmpty()){
                for (String s : store) {
                    printWriter.print( s + " " );
                }
                printWriter.print( "\n" );
            }

            // Write 100 integers randomly to a file
            for (int i = 0; i < 100 ; i++){
                int random = (int)(Math.random() * 100 + 1);
                printWriter.print(random);
                printWriter.print(" ");
            }
        }
    }



}
