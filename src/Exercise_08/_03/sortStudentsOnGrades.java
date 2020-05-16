package Exercise_08._03;

public class sortStudentsOnGrades {
    /** Main method */
    public static void main(String args[]) { // github
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

        // Count correct answer for student
        int[] correctAnswerCount = new int[answers.length];

        // Grade all answers
        for (int i = 0; i < answers.length; i++) {

            for (int j = 0; j < answers[i].length; j++) {
                if (answers[i][j] == keys[j])
                    correctAnswerCount[i]++;
            }

        }

        // Display student in increasing order of the number of correct answers
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

    }
}
