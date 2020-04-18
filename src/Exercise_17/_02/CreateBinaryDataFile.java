package Exercise_17._02;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBinaryDataFile {
    public static void main(String[] args) throws IOException {
        try (
                // True mean to add the new data to the old
                DataOutputStream outputStream = new DataOutputStream( new FileOutputStream( "Exercise17.02.dat" , true));
        ) {
            for (int i = 0; i < 100 ; i++)
                outputStream.writeInt( (int)(Math.random() * 100000) );

        }

        System.out.println("Done");
    }
}
