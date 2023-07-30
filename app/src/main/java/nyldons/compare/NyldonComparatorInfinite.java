package nyldons.compare;

import nyldons.order.NyldonOrder;

public class NyldonComparatorInfinite implements NyldonComparator{

    final NyldonOrder order;

    public NyldonComparatorInfinite(NyldonOrder order) {
        this.order = order;
    }

    public int compare(final String o1, final String o2) {

        final var lengthA = o1.length();
        final var lengthB = o2.length();
        final var comparisonLength = findLCM(lengthA, lengthB);

        for (int i = 0; i < comparisonLength; i++) {
            final var indexA = i % lengthA;
            final var indexB = i % lengthB;
            final var charA = o1.charAt(indexA);
            final var charB = o2.charAt(indexB);
            final var comparison = order.compare(charA, charB, i);
            if (comparison != 0)
                return comparison;
        }
        return 0;
    }

    private int findLCM(int a, int b) {
        final int greater = Math.max(a, b);
        final int smallest = Math.min(a, b);
        for (int i = greater;; i += greater) {
            if (i % smallest == 0)
                return i;
        }
    }

}
