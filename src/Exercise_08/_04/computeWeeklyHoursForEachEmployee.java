package Exercise_08._04;
/*********************************************************************************
 * (Compute the weekly hours for each employee) Suppose the weekly hours for all  *
 * employees are stored in a two-dimensional array. Each row records an employee’s*
 * seven-day work hours with seven columns. For example, the following            *
 * array stores the work hours for eight employees. Write a program that displays *
 * employees and their total hours in decreasing order of the total hours.        *
 *********************************************************************************/
public class computeWeeklyHoursForEachEmployee {
    public static void main(String[] args) {
        // In every index include the hours for each employee
        int[][] weeklyHours = { {2, 4, 3, 4, 5, 8, 8},
                                {7, 3, 4, 3, 3, 4, 4},
                                {3, 3, 4, 3, 3, 2, 2},
                                {9, 3, 4, 7, 3, 4, 1},
                                {3, 5, 4, 3, 6, 3, 8},
                                {3, 4, 4, 6, 3, 4, 4},
                                {3, 7, 4, 8, 3, 8, 4},
                                {6, 3, 5, 9, 2, 7, 9} };

        // Total hours for each employee
        int[][] totalHours = new int[weeklyHours.length][2];

        // Call methods
        computeTotalHours(weeklyHours, totalHours);
        sort(totalHours);
        displayResult(totalHours);
    }

    /** Compute total hours in 2D Array */
    public static void computeTotalHours(int[][] weeklyHours, int[][] totalHours) {
        for (int i = 0; i < weeklyHours.length; i++) {
            totalHours[i][0] = i;
            for (int j = 0; j < weeklyHours[i].length ; j++) {
                totalHours[i][1] += weeklyHours[i][j];
            }
        }
    }

    /** Sort 2D Array */
    public static void sort(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int employ = array[i][0];
            int employRowIndex = i;
            int max = array[i][1];
            int maxRowIndex = 1;
            for (int j = i+1; j < array.length; j++) {
                if (max < array[j][1]) {
                    max = array[j][1];
                    maxRowIndex = j;
                    employ = array[j][0];
                    employRowIndex = j;
                }
            }
            if (max != array[i][1]) {
                array[employRowIndex][0] = array[i][0];
                array[i][0] = employ;
                array[maxRowIndex][1] = array[i][1];
                array[i][1] = max;
            }
        }
    }

    /** Display result */
    public static void displayResult(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Employee " + array[i][0] + " sum of hours is " + array[i][1]);
        }
    }
}

