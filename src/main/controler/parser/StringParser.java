package main.controler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// https://docs.oracle.com/javase/8/docs/api/index.html?java/util/StringTokenizer.html

public class StringParser implements IParser {

    @Override
    public List<String> parse(String input) {
        StringTokenizer st = new StringTokenizer(input);
        List<String> output = new ArrayList<>();
        while (st.hasMoreTokens()) {
            output.add(st.nextToken());
        }
        return output;
    }
}
