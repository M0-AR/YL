package Exercise_28._01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TestGraphIfConnected {
    public static void main(String[] args) {
        try {

            URL url = new URL("http://liveexample.pearsoncmg.com/test/GraphSample1.txt");

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            // Number of vertices
            String line = in.readLine();
            int numberOfVertices = Integer.parseInt( line );


            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
