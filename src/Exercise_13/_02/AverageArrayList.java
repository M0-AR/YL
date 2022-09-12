package Exercise_13._02;

import java.util.ArrayList;

public class AverageArrayList {
    public static int average(ArrayList<Integer> list) {
        int avr = 0, size = list.size();
        for (int i = 0; i < size; i++) {
            avr += list.get(i);
        }
        return avr / size;
    }
}
