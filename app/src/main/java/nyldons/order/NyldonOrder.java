package nyldons.order;

public interface NyldonOrder {
    int compare(char o1, char o2, int index);

    String name();

    default boolean worksWithAlphabet(int size) {
        return true;
    }
}
