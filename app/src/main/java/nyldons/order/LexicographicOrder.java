package nyldons.order;

public class LexicographicOrder implements NyldonOrder{

        public int compare(char o1, char o2, int index) {
            return Character.compare(o1, o2);
        }
}
