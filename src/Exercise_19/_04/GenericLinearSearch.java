package Exercise_19._04;

public class GenericLinearSearch {
    public static void main(String[] args) {
        Integer[] list = {3, 4, 5, 1, -3, -5, -1};
        System.out.println(linearSearch(list, 2));
        System.out.println(linearSearch(list, 5));

        Double[] list1 = {3.0, 4.0, 5.0, 1.0, -3.0, -5.0, -1.0};
        System.out.println(linearSearch(list1, 2.0));
        System.out.println(linearSearch(list1, 5.0));
    }

    public static <E extends Comparable<E>> int linearSearch(E[] list, E key)
    {
        for (int i = 0; i < list.length; i++)
            if (list[i].equals( key ))
                return i;
        return -1;
    }


}
