package nyldons.compare;

import nyldons.order.NyldonOrder;

public class NyldonNaturalComparator implements NyldonComparator {
        final NyldonOrder order;

        public NyldonNaturalComparator(NyldonOrder order) {
            this.order = order;
        }
        public int compare(final String o1, final String o2) {
            var cmpLenght = Math.min(o1.length(), o2.length());
            for (int i = 0; i < cmpLenght; i++) {
                final var charA = o1.charAt(i);
                final var charB = o2.charAt(i);
                final var comparison = order.compare(charA, charB, i);
                if (comparison != 0)
                    return comparison;
            }

            return Integer.compare(o1.length(), o2.length());
        }
}
