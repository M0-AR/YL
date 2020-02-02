package Exercise_05._07;
/*
(Financial application: compute future tuition) Suppose that the tuition for a university
is $10,000 this year and increases 5% every year. In one year, the tuition
will be $10,500. Write a program that computes the tuition in ten years and the
total cost of four years’ worth of tuition after the tenth year.
*/
public class ComputeFutureTuition {
    public static void main(String[] args) {
        // Tuition for a university is $10,000 this year
        double tuition = 10000;
        int year = 1;
        //
        while (year <= 14){
            tuition *= 1.05;
            // Display the cost of tuition in ten years
            if(year == 10) System.out.println("Tuition in Ten years " + tuition);

            year++;
        }

        // Display the cost of four years' worth of tuition after tenth year
        System.out.println("Tuition in fourteen years " + tuition);
    }
}
