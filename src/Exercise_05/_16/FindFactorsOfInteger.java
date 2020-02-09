package Exercise_05._16;

public class FindFactorsOfInteger {
    public static void main(String[] args) {
        
        int number = 120;
        int divide = 2;
        StringBuilder result = new StringBuilder();
        
        while (number != 1){
            if (number % divide ==  0) {
                result.append( divide ).append( " " );
                number /= divide;
            } else 
                divide++;
        }

        System.out.println(result);
        
    }
}
