package Exercise_18._10;

public class OccurrencesOfCharacterInString {
    static int count = 0;
    public static void main(String[] args) { // github and y answer is 0 because numberOfOccurrences inside the recursion method
        System.out.println(count("welcome", 'e'));
    }

    public static int count(String str, char a){
        int numberOfOccurrences = 0;
        if (count < str.length()){
            if (str.charAt( count++ ) == a)
                numberOfOccurrences++;
            count(str, a);
        }

        return numberOfOccurrences;
    }
}
