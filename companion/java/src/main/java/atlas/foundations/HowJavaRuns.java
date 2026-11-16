package atlas.foundations;

public final class HowJavaRuns {
    private HowJavaRuns() {
    }

    public static void main(String[] args) {
        String language = "Java";
        int chapters = 42;
        System.out.println(language + " in " + chapters + " chapters.");
        System.out.println("Arguments: " + String.join(", ", args));
    }
}
