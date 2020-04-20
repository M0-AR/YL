package Exercise_17._03;


import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class SumAllIntegersInBinaryDataFile { // github
    public static void main(String[] args) throws IOException {
        int sum = 0 ; // Sum the number in the dat file
        try (
                DataInputStream input = new DataInputStream( new FileInputStream( "Exercise17.02.dat" ) )
        ){
            while (true)
                sum += input.readInt();

        } catch (EOFException ex) { // Detect the end of the file
            System.out.println("Sum of the integers is: " + sum);
        }
    }
}
