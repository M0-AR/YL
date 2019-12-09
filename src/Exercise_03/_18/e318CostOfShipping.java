package Exercise_03._18;
/*
(Cost of shipping) A shipping company uses the following function to calculate
the cost (in dollars) of shipping based on the weight of the package (in
pounds).

							 3.5, if 0 < w < = 1
			     c(w) =  5.5, if 1 < w < = 3
							 8.5, if 3 < w < = 10
							 10.5, if 10 < w < = 20

*/
public class e318CostOfShipping {
    public static void main(String[]args){

        //1
        double w, cost;

        //2
        w = 6;

        //3
        if(w <=0){
            System.out.println("Invalid input");
        }else if(w <= 1){
            System.out.println("The shipping cost is $3.5");
        }else if(w <= 3){
            System.out.println("The shipping cost is $5.5");
        }else if (w <= 10){
            System.out.println("The shipping cost is $8.5");
        }else if (w <= 20){
            System.out.println("The shipping cost is $10.5");
        }else {
            System.out.println("The package cannot be shipped.");
        }
    }
}
