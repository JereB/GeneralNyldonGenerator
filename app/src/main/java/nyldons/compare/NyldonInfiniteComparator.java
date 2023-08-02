package nyldons.compare;

import nyldons.order.NyldonOrder;

public class NyldonInfiniteComparator implements NyldonComparator {

    final NyldonOrder order;

    @Override
    public String fileName() {
        return order.name() + "-" + "InfiniteComp.csv";
    }

    public NyldonInfiniteComparator(NyldonOrder order) {
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
            final var comparison = order.compare(charA, charB, i + 1);
            if (comparison != 0)
                return comparison;
        }
        return 0;
    }

    private int findLCM(int a, int b) {
        final int greater = Math.max(a, b);
        final int smallest = Math.min(a, b);

        if (smallest == 0) {
            return 0;
        }

        for (int i = greater; ; i += greater) {
            if (i % smallest == 0)
                return i;
        }
    }

}
