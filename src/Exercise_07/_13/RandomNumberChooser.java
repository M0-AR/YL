package Exercise_07._13;

public class RandomNumberChooser {
    public static void main(String[] args) {
        System.out.println(getRandom( 14 ));
    }


    /** Return random number between 1 - 54 excluding the numbers
     * passed in the argument*/
    public static int getRandom(int... numbers){
        int random = numbers[0];

        while (random == numbers[0])
            random = (int)(Math.random() * 54) + 1;

        return random;
    }
}
