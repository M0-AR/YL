package Exercise_11._06;


import Exercise_17._06.Loan;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Date;

public class ObjectsToString {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Loan());
        list.add("ABC");
        list.add(new Date());
        list.add(new Circle());

        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}
