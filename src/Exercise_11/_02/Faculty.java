package Exercise_11._02;

public class Faculty extends Employee{
    public static int LECTURER = 1;
    public static int ASSISTANT_PROFESSOR = 2;
    public static int ASSOCIATE_PROFESSOR = 3;
    public static int PROFESSOR = 4;

    protected String officeHours;
    protected int rank;

    Faculty(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Faculty's name: " + getName();
    }
}
