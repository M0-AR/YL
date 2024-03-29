package Exercise_11._02;

public class Person {
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected String email;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person's name: " + name;
    }
}
