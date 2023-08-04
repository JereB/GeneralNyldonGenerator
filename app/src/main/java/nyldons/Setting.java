package nyldons;

import nyldons.alphabets.Alphabet;
import nyldons.alphabets.AlphabetUtil;
import nyldons.compare.NyldonComparator;
import nyldons.compare.NyldonInfiniteComparator;
import nyldons.compare.NyldonNaturalComparator;
import nyldons.order.LexicographicOrder;
import nyldons.order.NyldonOrder;
import nyldons.predicates.NyldonPredicate;
import nyldons.predicates.SuffixSmallerThanWord;

import java.nio.file.Path;

public class Setting {

    // Settings for Word-Generation
    final public static NyldonOrder order = new LexicographicOrder();

    final public static ComparisonType comparisonType = ComparisonType.Infinite;

    final public static int maxLength = 10;

    final public static Mode mode = Mode.Generate;

    final public static NyldonPredicate predicate = new SuffixSmallerThanWord();

    final public static Alphabet alphabet = AlphabetUtil.getAlphabet(3);

    // Helper
    public static NyldonComparator getComparator() {
        if (comparisonType == ComparisonType.Normal) {
            return new NyldonNaturalComparator(order);
        } else {
            return new NyldonInfiniteComparator(order);
        }
    }

    public static String getFilename() {
        return alphabet.name() + "+" + getComparator().fileName();
    }

    public static Path getInputFilePath() {
        return getOutputPath().resolve(getFilename());
    }

    public enum ComparisonType {
        Normal, Infinite
    }

    public enum Mode {
        Generate, Check
    }

    public static Path getOutputPath() {
        return Path.of("output");
    }

    public static Path getOutputFilePath() {
        return switch (mode) {
            case Generate -> getInputFilePath();
            case Check -> getOutputPath().resolve(predicate.getPredicateName() + "_" + getFilename());
        };
    }

}
