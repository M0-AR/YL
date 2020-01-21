package Exercise_04._25;
/*
(Generate vehicle plate numbers) Assume a vehicle plate number consists of three
uppercase letters followed by four digits. Write a program to generate a plate
number.
*/
public class GenerateVehiclePlateNumbers {
    public static void main(String[] args) {
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

