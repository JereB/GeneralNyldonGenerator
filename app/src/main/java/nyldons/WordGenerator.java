package nyldons;

import java.util.Iterator;

public class WordGenerator implements Iterator<String> {

    final int length;
    final int end;
    int status;

    public WordGenerator(int length) {
        this.length = length;
        this.status = 0;
        this.end = (1 << length);
    }

    @Override
    public boolean hasNext() {
        return status < end;
    }

    @Override
    public String next() {
        var result = getBinaryString();
        status++;
        return result;
    }

    // return the binary string with leading zeros to length
    private String getBinaryString(){
        var result = Integer.toBinaryString(status);
        if (result.length() < length) {
            var zeros = length - result.length();
            result = "0".repeat(zeros) + result;
        }
        return result;
    }
}
