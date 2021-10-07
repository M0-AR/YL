package Exercise_11._02;

import Exercise_10._14.MyDate;

public class Staff extends Employee{
    private String title;


    public Staff(String name, String address, String phoneNumber, String email, String office, double salary, MyDate dateHired, String title) {
        super(name, address, phoneNumber, email, office, salary, dateHired);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff's name: " + name;
    }
}
