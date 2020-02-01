package Exercise_05._06;

public class MilesToKilometersAndKilometersToMiles { // g and y
    public static void main(String[] args) {
        final double MILES_TO_KILOMETERS = 1.609;
        System.out.println("Miles\t\tKilometers\t\t|\t\tKilometres\t\tMiles");

        for (int miles = 1, kilometers = 20; miles<=10 && kilometers <= 65; kilometers+=5, miles++){
            System.out.printf( "%-7d %10.3f %10s %8d %19.3f\n", miles, (kilometers*MILES_TO_KILOMETERS)
                    ,"|",kilometers,(kilometers/MILES_TO_KILOMETERS));
        }
    }
}
