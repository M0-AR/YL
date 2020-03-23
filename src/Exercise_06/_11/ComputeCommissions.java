package Exercise_06._11;

public class ComputeCommissions {
    public static void main(String[] args) { // github
        System.out.printf( "%-15s%-15s\n", "SalesAmount", "Commission" );
        System.out.println("----------------------------");

        for (int salesAmount = 10000; salesAmount <= 100000; salesAmount+= 5000) {
            System.out.printf( "%-15d%-15.1f\n",salesAmount,computeCommission( salesAmount ) );
        }



    }

    public static double computeCommission(double salesAmount){
        return (5000*0.08) + (5000*0.1) + ((salesAmount-10000) * 0.12);
    }
}
