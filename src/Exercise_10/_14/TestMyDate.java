package Exercise_10._14;

public class TestMyDate {
    public static void main(String[] args) {
        MyDate d1 = new MyDate();
        //MyDate d2 = new MyDate(34355555133101L);
        MyDate d2 = new MyDate(561555550000L);

        System.out.println("D1: " +
                "\nYear: " + d1.getYear() +
                "\nMonth: " + d1.getMonth() +
                "\nDay: " + d1.getDay());
        System.out.println();
        System.out.println("D2: " +
                "\nYear: " + d2.getYear() +
                "\nMonth: " + d2.getMonth() +
                "\nDay: " + d2.getDay());
    }

}
