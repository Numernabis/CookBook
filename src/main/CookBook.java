package main;

import main.controler.Conductor;
import main.controler.parser.StringParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CookBook {
    public static void main(String[] args){
        Conductor conductor = new Conductor(new StringParser());

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String line = br.readLine();
                List<String> lines = conductor.parser.parse(line);
                if(line.equals("quit") || line.equals("q") || line.equals("/q") || line.equals("/quit"))
                    return;
                else {
                    conductor.executionStrategy.execute(lines);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
