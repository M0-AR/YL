package Exercise_11._02;

enum Status {FRESHMAN, SOPHOMORE, JUNIOR, SENIOR}
public class Student extends Person{
    private final Status status;

    public Student(String name, String address, String phoneNumber, String email, Status status) {
        super(name, address, phoneNumber, email);
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student's name: " + name;
    }
}
