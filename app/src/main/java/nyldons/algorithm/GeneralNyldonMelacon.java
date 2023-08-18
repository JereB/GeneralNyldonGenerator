package nyldons.algorithm;

import nyldons.compare.NyldonComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralNyldonMelacon {
    private final NyldonComparator comparator;


    public GeneralNyldonMelacon(NyldonComparator comparator) {
        this.comparator = comparator;
    }

    public String execute(String word) {
        // Factorization of word into letters as list of length |word|
        var temp = word.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        var facOfWord = new ArrayList<>(temp);

        while (facOfWord.size() > 1) {
            var min = facOfWord.stream().min(comparator).get();

            // indices must be one smaller, as they begin with 0
            for (int i = 0; i < temp.size(); i++) {

                if (isEqual(temp.get(i), min) && isPreviousBigger(temp, i)) {
                    // the factor is appened to the larger factor on its left
                    concatToPrevious(temp, i);
                }
            }
            facOfWord = new ArrayList<>(temp);
        }


        return facOfWord.get(0);
    }

    private boolean isEqual(String word1, String word2) {
        return comparator.compare(word1, word2) == 0;
    }

    private boolean isPreviousBigger(List<String> list, int index) {
        int previous = switch (index) {
            case 0 -> list.size() - 1;
            default -> index - 1;
        };
        return comparator.compare(list.get(previous), list.get(index)) > 0;
    }

    private String getLast(List<String> list) {
        return list.get(list.size() - 1);
    }

    private void concatToPrevious(List<String> list, int index) {
        var toConcat = switch (index) {
            case 0 -> list.size() -1;
            default -> index - 1;
        };
        list.set(toConcat, list.get(toConcat) + list.get(index));
        list.remove(index);
    }

}
