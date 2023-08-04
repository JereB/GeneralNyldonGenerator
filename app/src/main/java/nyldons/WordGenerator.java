package nyldons;

import nyldons.alphabets.Alphabet;


import java.util.Iterator;

public class WordGenerator implements Iterator<String> {
    final Alphabet alphabet;
    final int length;
    final int end;
    int status;

    public WordGenerator(int length, Alphabet alphabet) {
        this.length = length;
        this.status = 0;
        this.end = alphabet.shortestWithLength(length);
        this.alphabet = alphabet;
    }

    @Override
    public boolean hasNext() {
        return status < end;
    }

    @Override
    public String next() {
        var result = alphabet.convert(status, length);
        status++;
        return result;
    }
}
