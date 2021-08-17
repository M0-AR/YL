package Exercise_10._23;

public class MyString2 {
    private String str;

    public MyString2(String str) {
        this.str = "";
        for (int i = 0; i < str.length(); i++){
            this.str += str.charAt(i) + "";
        }
    }

    /** Return an integer greater than 0, equal to 0,
     *  or less than 0 to indicate whether this string
     *  is greater than, equal to, or less than this str.*/
    public int compare(String s) {
        int index = 0;

        if (this.str.length() < s.length()) {
            index = -1;
        }
        if (s.length() < this.str.length()) {
            index = 1;
        }

        for (int i = 0; i < this.str.length(); i++) {
            if (this.str.charAt(i) < s.charAt(i))
                index = -(i + 1);
            if (this.str.charAt(i) > s.charAt(i))
                index = i + 1;
        }
        return index;
    }

    /** Return a new myString2 object that start
     *  with a specified begin.*/
    public MyString2 substring(int begin) {
        StringBuilder sub = new StringBuilder();
        for (int i = begin; i < this.str.length(); i++) {
            sub.append(this.str.charAt(i));
        }
        return new MyString2(sub.toString());
    }

    /** Return new MyString2 object in upper case characters */
    public MyString2 toUpperCase() {
        String s = "";
        for (int i = 0; i < this.str.length(); i++) {
            s += Character.toUpperCase(this.str.charAt(i));
        }
        return new MyString2(s);
    }

    /** Return string as an array of characters */
    public char[] toChars() {
        char[] temp = new char[this.str.length()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = this.str.charAt(i);
        }
        return temp;
    }

    /** Return a boolean format as a MyString2 object */
    public static MyString2 valueOf(boolean b) {
        return b ? new MyString2("true") : new MyString2("false");
    }
}
