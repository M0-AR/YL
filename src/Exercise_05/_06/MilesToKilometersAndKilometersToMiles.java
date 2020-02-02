package Exercise_05._06;
/*
(Conversion from miles to kilometers) Write a program that displays the following
two tables side by side:
Miles         Kilometers   |   Kilometers     Miles
1             1.609        |   20             12.430
2             3.218        |   25             15.538
...
9             14.481       |   60             37.290
10            16.090       |   65             40.398
*/
public class MilesToKilometersAndKilometersToMiles {
    public static void main(String[] args) {
        final double MILES_TO_KILOMETERS = 1.609;
        System.out.println("Miles\t\tKilometers\t\t|\t\tKilometres\t\tMiles");


        for (int miles = 1, kilometers = 20; miles<=10 && kilometers <= 65; kilometers+=5, miles++){
            System.out.printf( "%-7d %10.3f %10s %8d %19.3f\n", miles, (kilometers*MILES_TO_KILOMETERS)
                    ,"|",kilometers,(kilometers/MILES_TO_KILOMETERS));
        }
    }
}
