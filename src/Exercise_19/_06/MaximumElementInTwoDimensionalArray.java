package Exercise_19._06;
/*********************************************************************************
 * (Maximum element in a two-dimensional array) Write a generic method that       *
 * returns the maximum element in a two-dimensional array.                        *
 *                                                                                *
 * public static <E extends Comparable<E>> E max(E[][] list)                      *
 *********************************************************************************/

public class MaximumElementInTwoDimensionalArray {
    public static void main(String[] args) {
        Integer[][] numbers = { {2, 17, 3}, {4, 4, 6} };
        System.out.println(max(numbers));
    }

    /** Method returns the maximum element in a two-dimensional array */
    public static <E extends Comparable<E>> E max (E[][] list){
        E max = list[0][0];


        for (int i = 0; i <list.length ; i++)
            for (int j = 0; j <list[i].length ; j++)
                if ( list[i][j].compareTo( max) > 0)
                    max = list[i][j];


        return max;
    }
}
