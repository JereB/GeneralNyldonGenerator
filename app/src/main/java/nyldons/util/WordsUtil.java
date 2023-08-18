package nyldons.util;

public class WordsUtil {

    public static boolean isPrimitive(String word) {
        for(int i = 1; i <= word.length() / 2; i++) {
            if(consistsOfPowerOfSize(word, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean consistsOfPowerOfSize(String word, int power) {
        if(word.length() % power != 0) {
            return false;
        }
        for(int i = 0; i < word.length(); i += power) {
            if(!word.substring(i, i + power).equals(word.substring(0, power))) {
                return false;
            }
        }
        return true;
    }
}
