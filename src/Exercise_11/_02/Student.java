package Exercise_11._02;

public class Student extends Person{
    public static int FRESHMAN = 1;
    public static int SOPHOMORE = 2;
    public static int JUNIOR = 3;
    public static int SENIOR = 4;

    protected int status;

    Student(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "Student's name: " + getName();
    }
}
