package Exercise_05._25;

public class ComputePi {
    public static void main(String[] args) { // github
        int i = 10000;
        double pi = 0;
        int counter = 0;
        for (int j = 1; j < 10000; j+=2) {
            pi += (counter++ % 2 == 0) ?   - (j / (j + 2)) :  j / (j + 2);
        }
    }
}
