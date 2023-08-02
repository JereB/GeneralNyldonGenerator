package nyldons.predicates;

import nyldons.compare.NyldonComparator;

import java.util.Collection;
import java.util.function.Predicate;

public interface NyldonPredicate extends Predicate<String> {
    default void setup(Collection<String> nyldons, NyldonComparator comp){}
    String getPredicateName();
}
