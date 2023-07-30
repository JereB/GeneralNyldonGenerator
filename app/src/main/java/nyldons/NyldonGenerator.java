package nyldons;

import nyldons.compare.NyldonComparator;

import java.util.ArrayList;
import java.util.List;

public class NyldonGenerator {
    private final NyldonComparator comparator;
    private final int maxLength;

    public NyldonGenerator(NyldonComparator comparator, int maxLength) {
        this.comparator = comparator;
        this.maxLength = maxLength;
    }

    public List<String> generate() {
        var nyldon = new ArrayList<String>();
        nyldon.add("0");
        nyldon.add("1");

        for (int i = 0; i < maxLength; i++) {
            var check = new NyldonChecker(comparator, nyldon);
            var newNyldon = new ArrayList<String>();
            var gen = new WordGenerator(i);
            while (gen.hasNext()) {
                var word = gen.next();
                if (check.isNyldonWord(word)) {
                    newNyldon.add(word);
                }
            }
            nyldon.addAll(newNyldon);
        }
        return nyldon;
    }

}
