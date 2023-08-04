package nyldons.alphabets;

public class BinaryAlphabet implements Alphabet {
    @Override
    public int size() {
        return 2;
    }

    @Override
    public String convert(int input, int length) {
        var result = Integer.toBinaryString(input);
        if (result.length() < length) {
            var zeros = length - result.length();
            result = "0".repeat(zeros) + result;
        }
        return result;
    }

    @Override
    public int shortestWithLength(int length) {
        return 1 << length;
    }

    @Override
    public String name() {
        return "binary";
    }
}
