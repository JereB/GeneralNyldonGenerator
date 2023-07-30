package nyldons;

import nyldons.compare.NyldonComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class NyldonChecker {

    final NyldonComparator comparator;
    final HashSet<String> nyldonWords;

    public NyldonChecker(NyldonComparator comparator, Collection<String> nyldonWords) {
        this.comparator = comparator;
        this.nyldonWords = new HashSet<>(nyldonWords);
    }

    public boolean isNyldonWord(String word) {
        return !existsOrderedFactorization(word, new ArrayList<>());
    }


    private boolean existsOrderedFactorization(String word, List<String> factorization) {

        if (word.length() == 0) {
            return isFactorizationOrdered(factorization);
        }

        for (int end = 1; end <= word.length(); end++) {
            String prefix = word.substring(0, end);
            if (nyldonWords.contains(prefix)) {
                String rest = word.substring(end);
                factorization.add(prefix);
                if (existsOrderedFactorization(rest, factorization)) {
                    return true;
                }
                factorization.remove(factorization.size() - 1);
            }
        }

        return false;
    }

    private boolean isFactorizationOrdered(List<String> factorization) {

        for (int i = 0; i < factorization.size() - 1; i++) {
            String current = factorization.get(i);
            String next = factorization.get(i + 1);

            // if there is one factor bigger then the nex one the factorization is not monotone increasing and therefore valid
            if (comparator.compare(current, next) > 0) {
                return false;
            }
        }

        return true;
    }

}
