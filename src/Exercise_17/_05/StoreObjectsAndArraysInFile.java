package Exercise_17._05;

import java.io.*;
import java.util.Date;

public class StoreObjectsAndArraysInFile {
    public static void main(String[] args) throws IOException {

        int[] n = {1,2,3,4,5};
        Date date = new Date(  );
        double d = 5.5;

        try (
             ObjectOutputStream output =  new ObjectOutputStream(
                     new FileOutputStream( "Exercise17.05.dat" ) )
        ){
            // To write int array in Exercise17.05.dat
            for (int i : n)
                output.write( i );

            // To write date object in Exercise17.05.dat
            output.writeObject( date );

            // To write double value in Exercise17.05.dat
            output.writeDouble( d );
        }




    }
}
