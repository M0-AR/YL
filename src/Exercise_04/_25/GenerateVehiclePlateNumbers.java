package Exercise_04._25;

public class GenerateVehiclePlateNumbers {
    public static void main(String[] args) { // g
        String vehiclePlate = "";

        int threeUppercaseLetters = 3;
        for (int i = 0; i < threeUppercaseLetters; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            vehiclePlate += ch;
        }

        int fourDigits = 4;
        for (int i = 0; i < fourDigits; i++) {
            int number  = (int)( Math.random() * 9);
            vehiclePlate += number;
        }

        // Display result
        System.out.println(vehiclePlate);
    }
}

