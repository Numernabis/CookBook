package main;

import main.controler.Conductor;
import main.controler.parser.StringParser;
import main.model.book.*;
import main.model.category.*;
import main.model.recipe.*;
import main.model.ingredient.*;
import main.model.unit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CookBook {
    public static void main(String[] args) {
        Conductor conductor = new Conductor(new StringParser());

        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            if(i % 2 == 0) b.append('\u2615');
            else b.append(' ');
        }

        String info = "Welcome in CookBook :)\n";
        info += b;
        info += "\nTo get available commands use /support.\n";
        System.out.println(info);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String singleLine = br.readLine();
                List<String> lines = conductor.parser.parse(singleLine);

                if (singleLine.equals("quit") || singleLine.equals("q")
                        || singleLine.equals("/quit") || singleLine.equals("/q"))
                    return;
                else {
                    conductor.executionStrategy.execute(lines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
