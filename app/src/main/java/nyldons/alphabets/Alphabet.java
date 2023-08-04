package nyldons.alphabets;

public interface Alphabet {
    int size();
    String convert(int input, int length);
    int shortestWithLength(int length);

    String name();
}
