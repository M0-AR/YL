package Exercise_05._07;

public class ComputeFutureTuition {
    public static void main(String[] args) { // g
        // Tuition for a university is $10,000 this year
        double tuition = 10000;
        int year = 1;
        //
        while (year <= 10){
            tuition *= 1.05;
            if(year == 4) System.out.println("Tuition in four years " + tuition);
            year++;
        }

        System.out.println("Tuition in Ten years " + tuition);
    }
}
