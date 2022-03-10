package baraa.dwbs.mapreduce;

import baraa.dwbs.mapreduce.wordcounter.Config;
import baraa.dwbs.mapreduce.wordcounter.WordCounter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Config config = new Config();

        for (String arg : args) {
            String k = arg.split("-")[0];
            String v = arg.split("-")[1];

            switch (k) {
                case "machines" -> config.machinesCount = Integer.parseInt(v);
                case "input" -> config.inputFile = new File(v);
                case "output" -> config.outputFile = new File(v);
            }
        }

        WordCounter wordCounter = new WordCounter(config);
        wordCounter.count();
    }

}
