package Exercise_19._06;

public class MaximumElementInTwoDimensionalArray {
    public static void main(String[] args) {

    }

    public static <E extends Comparable<E>> E max (E[][] list){
        E max = list[0][0];


        for (int i = 0; i <list.length ; i++)
            for (int j = 0; j <list[i].length ; j++)
                if ( list[i][j].compareTo( max) > 0)
                    max = list[i][j];


        return max;
    }
}
