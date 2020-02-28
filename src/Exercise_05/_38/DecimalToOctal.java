package Exercise_05._38;

public class DecimalToOctal { // github and y
    public static void main(String[] args) {
        double decimal = 3949;
        String  octal = "";


        String[] split;

        while (decimal >= 1){
            decimal /= 8;

            // Store the decimal in string and split it by comma in to array
            split = String.valueOf( decimal ).split( "\\." );

            // add 0. to th decimal number
            split[1] = "0." + split[1];
            // Store the output in octal
            octal = (int)(Double.parseDouble( split[1] ) * 8) + octal;
        }

        // Display result
        System.out.println(octal);

    }
}
