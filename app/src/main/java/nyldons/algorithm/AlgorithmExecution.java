package nyldons.algorithm;

import nyldons.NyldonGenerator;
import nyldons.WordGenerator;
import nyldons.alphabets.Alphabet;
import nyldons.alphabets.AlphabetUtil;
import nyldons.alphabets.BinaryAlphabet;
import nyldons.compare.NyldonInfiniteComparator;
import nyldons.order.LexicographicOrder;
import nyldons.order.NyldonOrder;
import nyldons.util.WordsUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class AlgorithmExecution {

    private static final Logger LOGGER = Logger.getLogger(AlgorithmExecution.class.getName());

    private static final int MAX_WORD_LENGTH = 10;

    private static final List<Alphabet> ALPHABETS = List.of(new BinaryAlphabet(),
            AlphabetUtil.getAlphabet(3),
            AlphabetUtil.getAlphabet(4),
            AlphabetUtil.getAlphabet(5),
            AlphabetUtil.getAlphabet(6),
            AlphabetUtil.getAlphabet(7),
            AlphabetUtil.getAlphabet(8),
            AlphabetUtil.getAlphabet(9),
            AlphabetUtil.getAlphabet(10));

    private static final List<NyldonOrder> ORDERINGS = List.of(new LexicographicOrder());

    public static void main(String[] args) {

        List<String> errors = new ArrayList<>();

        for(var alphabet : ALPHABETS) {
            for(var ordering : ORDERINGS) {
                var cmp = new NyldonInfiniteComparator(ordering);
                if (!ordering.worksWithAlphabet(alphabet.size())) {
                    LOGGER.warning("Ordering " + ordering.name() + " does not work with alphabet " + alphabet.name());
                    continue;
                }
                var nyldonGenerator = new NyldonGenerator(cmp, MAX_WORD_LENGTH , alphabet);
                var nyldons = new HashSet<>(nyldonGenerator.generate());
                LOGGER.info("Starting algorithm with alphabet " + alphabet.name() + " and ordering " + ordering.name());

                var algorithm = new GeneralNyldonMelacon(cmp);

                for (int i = 2; i < MAX_WORD_LENGTH; i++) {
                    var wordGenerator = new WordGenerator(i, alphabet);
                    LOGGER.info("Starting algorithm with alphabet " + alphabet.name() + " and ordering " + ordering.name() + " and word length " + i);
                    while (wordGenerator.hasNext()) {
                        var word = wordGenerator.next();

                        if (WordsUtil.isPrimitive(word)) {
                            var result = algorithm.execute(word);
                            if (!nyldons.contains(result)) {
                                LOGGER.warning("Algorithm returned " + result + " for word " + word + " but it is not a nyldon word");

                                errors.add("Word: " + word + " Algorithm: " + result + " ordering:" + ordering.name() + " alphabet: " + alphabet.name());
                            }
                        }

                    }
                }

            }
        }

        errors.forEach(System.out::println);
    }

}
