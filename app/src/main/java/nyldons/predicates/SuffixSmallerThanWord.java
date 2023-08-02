package nyldons.predicates;

import nyldons.compare.NyldonComparator;

import java.util.Collection;

public class SuffixSmallerThanWord implements NyldonPredicate{

    private NyldonComparator comp;

    @Override
    public String getPredicateName() {
        return "SuffixSmallerThanWord";
    }

    @Override
    public void setup(Collection<String> nyldons, NyldonComparator comp) {
        this.comp = comp;
    }

    @Override
    public boolean test(String s) {
        for (int i = s.length(); i > 0; i--) {
            String suffix = s.substring(i);

            if (comp.compare(suffix, s) > 0) {
                return false;
            }
        }
        return true;
    }
}
