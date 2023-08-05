package nyldons;

import nyldons.alphabets.Alphabet;
import nyldons.compare.NyldonComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NyldonGenerator {
    private static final Logger logger = Logger.getLogger(NyldonGenerator.class.getName());
    private final NyldonComparator comparator;
    private final int maxLength;

    private final Alphabet alphabet;

    public NyldonGenerator(NyldonComparator comparator, int maxLength, Alphabet alphabet) {
        this.comparator = comparator;
        this.maxLength = maxLength;
        this.alphabet = alphabet;
    }

    public List<String> generate() {
        var nyldon = new ArrayList<String>();

        // Add all letters
        for (int i = 0; i < alphabet.size(); i++) {
            nyldon.add(alphabet.convert(i, 1));
        }

        for (int i = 0; i < maxLength; i++) {
            var startTime = System.currentTimeMillis();
            var check = new NyldonChecker(comparator, nyldon);
            var newNyldon = new ArrayList<String>();
            var gen = new WordGenerator(i, alphabet);
            while (gen.hasNext()) {
                var word = gen.next();
                if (check.isNyldonWord(word)) {
                    newNyldon.add(word);
                }
            }
            logger.info("Number of nyldons for length " + i + ": " + newNyldon.size());
            logger.info("Time taken for length " + i + ": " + (System.currentTimeMillis() - startTime) + "ms");
            nyldon.addAll(newNyldon);
        }
        return nyldon;
    }

}
