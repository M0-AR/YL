package BookExercise._04.CheckPointQuestions;

public class _4_20 {
    public static void main(String []args){
        String s1 = "Welcome";
        String s2 = "welcome";
//        a. Check whether s1 is equal to s2 and assign the result to a Boolean variable isEqual.
        System.out.println(s1.equals(s2));
//        b. Check whether s1 is equal to s2, ignoring case, and assign the result to a Boolean variable isEqual.
        System.out.println(s1.equalsIgnoreCase(s2));
//        c. Compare s1 with s2 and assign the result to an int variable x.
        int x = s1.compareTo( s2 );
        System.out.println(x);
//        d. Compare s1 with s2, ignoring case, and assign the result to an int variable x.
        int xd = s1.compareToIgnoreCase( s2 );
        System.out.println(xd);
//        e. Check whether s1 has the prefix AAA and assign the result to a Boolean variable b.
        Boolean b1 = (s1.startsWith( "AAA" ));
        System.out.println(b1);
//        f. Check whether s1 has the suffix AAA and assign the result to a Boolean variable b.
        Boolean b2 = (s1.endsWith( "AAA" ));
        System.out.println(b2);
//        g. Assign the length of s1 to an int variable x.
        int xg = s1.length();
        System.out.println(xg);
//        h. Assign the first character of s1 to a char variable x.
        char xh = s1.charAt( 0 );
        System.out.println(xh);
//                i. Create a new string s3 that combines s1 with s2.
        String s3 = s1 + s2;
        System.out.println(s3);
//        j. Create a substring of s1 starting from index 1.
        System.out.println(s1.substring( 1 ));
//        k. Create a substring of s1 from index 1 to index 4.
        System.out.println(s1.substring( 1, 5));
//        l. Create a new string s3 that converts s1 to lowercase.
        String s3l = s1.toLowerCase();
        System.out.println(s3l);
//        m. Create a new string s3 that converts s1 to uppercase.
        String s3m = s1.toUpperCase();
        System.out.println(s3m);
//        n. Create a new string s3 that trims whitespaces on both ends of s1.
        String s3n =  s1.trim();
        System.out.println(s3n);
//        o. Assign the index of the first occurrence of the character e in s1 to an int variable x.
        int xo = s1.indexOf( 'e' );
        System.out.println(xo);
//        p. Assign the index of the last occurrence of the string abc in s1 to an int
        int xp = s1.lastIndexOf( "abc" );
        System.out.println(xp);
    }
}