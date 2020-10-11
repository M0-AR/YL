package Exercise_08._21;
/**
 * (Central city) Given a set of cities, the central city is the city that has the shortest
 * total distance to all other cities. Write a program that prompts the user to enter
 * the number of cities and the locations of the cities (coordinates), and finds the
 * central city and its total distance to all other cities.
 * Input:
 * Enter the number of cities: 5
 * Enter the coordinates of the cities:
 *  2.5 5 5.1 3 1 9 5.4 54 5.5 2.1
 * Output:
 * The central city is at (2.5, 5.0)
 * The total distance to all other cities is 60.81
 * */
import java.util.Scanner;

public class CentralCity {

    public static void main(String[] args) {
        double[][] citiesCoordinates = { {2.5, 5},
                                         {5.1, 3},
                                         {1, 9},
                                         {5.4, 54},
                                         {5.5, 2.1} };

        // Prompt the user
        citiesCoordinates = promptTheUser();

        //
        double[][] distanceToAllOtherCites = calculateDistanceToAllOtherCities( citiesCoordinates );

        double[] sumOfDistance = calculateSumOfDistance(distanceToAllOtherCites);

        int indexOfCentralCity = findIndexOfSmallestDistance(sumOfDistance);

        // Print result
        System.out.println("The central city is at (" + citiesCoordinates[indexOfCentralCity][0] + ", "
                                                      + citiesCoordinates[indexOfCentralCity][1] + ")");
        System.out.println("The total distance to all other cities is " + (int) (100 * sumOfDistance[indexOfCentralCity]) / 100.0 );
    }

    private static double[][] promptTheUser() {
        Scanner input = new Scanner( System.in );
        System.out.print( "Enter the number of cities: " );
        int numberOfCities = input.nextInt();
        int numberOfPointCoordinate = 2;
        double[][]  citiesCoordinates = new double[numberOfCities][numberOfPointCoordinate];
        System.out.println("Enter the coordinates of the cities: ");
        for (int row = 0; row < numberOfCities; row++) {
            for (int column = 0; column < numberOfPointCoordinate; column++) {
                citiesCoordinates[row][column] = input.nextDouble();
            }
        }
        return citiesCoordinates;
    }

    private static double[][] calculateDistanceToAllOtherCities(double[][] citiesCoordinates) {
        double[][] distance = new double[citiesCoordinates.length][citiesCoordinates.length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                double x1 = citiesCoordinates[i][0];
                double x2 = citiesCoordinates[j][0];
                double y1 = citiesCoordinates[i][1];
                double y2 = citiesCoordinates[j][1];
                distance[i][j] = Math.sqrt( Math.pow( x2 - x1, 2 )
                                                + Math.pow( y2 - y1, 2 ));
            }
        }
        return distance;
    }

    private static double[] calculateSumOfDistance(double[][] distance) {
        double[] sumOfDistance = new double[distance.length];
        for (int i = 0; i < sumOfDistance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                sumOfDistance[i] += distance[i][j];
            }
        }
        return sumOfDistance;
    }

    private static int findIndexOfSmallestDistance(double[] sumOfDistance) {
        int indexOfSmallestDistance = 0;
        double smallestSumOfDistance = sumOfDistance[0];
        for (int i = 1; i < sumOfDistance.length; i++) {
            if (smallestSumOfDistance > sumOfDistance[i]) {
                smallestSumOfDistance = sumOfDistance[i];
                indexOfSmallestDistance = i;
            }
        }
        return indexOfSmallestDistance;
    }
}
