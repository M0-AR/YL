package Exercise_08._03;
/*********************************************************************************
 * (Sort students on grades) Rewrite Listing 8.2, GradeExam.java, to display the  *
 * students in increasing order of the number of correct answers.                 *
 *********************************************************************************/
public class sortStudentsOnGrades {
    /** Main method */
    public static void main(String args[]) {
        // Students' answers to the questions
        char[][] answers = {
                {'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
                {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
                {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
                {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}};

        // Key to the questions
        char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};

        // Students' number and correct answers
        int[][] correctCounts = new int[answers.length][2];


        // Grade all answers
        for (int i = 0; i < answers.length; i++) {
            // Grade one student
            for (int j = 0; j < answers[i].length; j++) {
                if (answers[i][j] == keys[j]){
                    correctCounts[i][0] = i;
                    correctCounts[i][1]++;
                }

            }

        }

        // Sort in increasing order according to the count of correct answers
        sort(correctCounts);

        // Display sorted matrix
        for (int i = 0; i < correctCounts.length; i++)
            System.out.println("Student " + correctCounts[i][0] +
                                "'s correct count is " + correctCounts[i][1]);



    }

    /** Sort according ot column 1 in increasing order*/
    public static void sort(int[][] m){
        for (int i = 0; i < m.length ; i++) {
            // min value
            int minCol0 = m[i][0];
            int minCol1 = m[i][1];
            int minIndex = i;

            for (int j = i + 1; j < m.length ; j++) {
                if (m[j][1] < minCol1){
                    minCol0 = m[j][0];
                    minCol1 = m[j][1];
                    minIndex = j;
                }
            }

            // swap
            if (minIndex != i){
                m[minIndex][0] = m[i][0];
                m[minIndex][1] = m[i][1];
                m[i][0] = minCol0;
                m[i][1] = minCol1;
            }

        }
    }


    /** Solution in one-dimension array*/
    // Count correct answer for student
    /*int[] correctAnswerCount = new int[answers.length];
    *        if (answers[i][j] == keys[j])
                    correctAnswerCount[i]++;*/
   /* public static void displayAndSort(int[] correctAnswerCount){
        for (int i = 0; i < correctAnswerCount.length; i++) {

            // min value
            int min = correctAnswerCount[i];
            int minIndex = i;

            for (int j = 0; j < correctAnswerCount.length ; j++) {
                if (correctAnswerCount[j] < min){
                    min = correctAnswerCount[j];
                    minIndex = j;
                }
            }

            System.out.println("Student " + minIndex + " has " + min +
                    " correct answers");
            correctAnswerCount[minIndex] = Integer.MAX_VALUE;
        }
    }*/
}
