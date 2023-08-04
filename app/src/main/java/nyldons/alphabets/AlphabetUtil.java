package nyldons.alphabets;

public class AlphabetUtil {

    public static Alphabet getAlphabet(int length) {
        return new GeneralizedAlphabet(length);
    }

    private record GeneralizedAlphabet(int length) implements Alphabet {
            private final static String NUMBERS = "0123456789ABCDEF";

        private GeneralizedAlphabet {
            if (length < 2 || length > NUMBERS.length()) {
                throw new IllegalArgumentException("length must be between 2 and " + NUMBERS.length());
            }
        }

            public int size() {
                return length;
            }

            public String convert(int input, int lenght) {
                var sb = new StringBuilder();
                while (input > 0) {
                    sb.append(NUMBERS.charAt(input % length));
                    input /= length;
                }
                sb.append("0".repeat(lenght - sb.length()));
                return sb.reverse().toString();
            }

            public int shortestWithLength(int length) {
                return (int) Math.pow(size(), length);
            }

            public String name() {
                return "alphsize" + size();
            }
        }
}