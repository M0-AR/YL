package Exercise_11._02;

public class Test_11_02 {
    public static void main(String[] args) {
        Person person = new Person("MD");
        Student student = new Student("DM");
        Employee employee = new Employee("MDM");
        Faculty faculty = new Faculty("DMD");
        Staff staff = new Staff("MDMD");

        System.out.println(person);
        System.out.println(student);
        System.out.println(employee);
        System.out.println(faculty);
        System.out.println(staff);
    }
}
