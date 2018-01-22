package main.controler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
