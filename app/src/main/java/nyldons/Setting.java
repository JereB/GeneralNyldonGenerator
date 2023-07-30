package nyldons;

import nyldons.compare.NyldonComparator;
import nyldons.compare.NyldonInfiniteComparator;
import nyldons.compare.NyldonNaturalComparator;
import nyldons.order.LexicographicOrder;
import nyldons.order.NyldonOrder;

import java.nio.file.Path;

public class Setting {

    // Settings for Word-Generation
    final public static NyldonOrder order = new LexicographicOrder();

    final public static ComparisionType comparisionType = ComparisionType.Infinite;

    final public static int maxLength = 10;


    // Helper
    public static NyldonComparator getComparator() {
        if (comparisionType == ComparisionType.Normal) {
            return new NyldonNaturalComparator(order);
        } else {
            return new NyldonInfiniteComparator(order);
        }
    }

    public static String getFilename() {
        return getComparator().fileName();
    }

    public enum ComparisionType {
        Normal, Infinite
    }

    public static Path getOutputPath() {
        return Path.of("output");
    }

    public static Path getOutputFilePath() {
        return getOutputPath().resolve(getFilename());
    }

}
