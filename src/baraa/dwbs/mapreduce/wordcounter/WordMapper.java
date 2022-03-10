package baraa.dwbs.mapreduce.wordcounter;

import baraa.dwbs.mapreduce.interfaces.Mapper;
import baraa.dwbs.mapreduce.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

public class WordMapper extends Thread implements Mapper<String, Integer, String, Integer> {

    private final List<String> words;
    private final List<Tuple<String, Integer>> mapResult;
    private final int mapperId;
    private static int mappers = 0;

    public WordMapper(List<String> words, List<Tuple<String, Integer>> mapResult) {
        this.words = words;
        this.mapResult = mapResult;
        this.mapperId = mappers++;
    }

    @Override
    public void run() {
        List<Tuple<String, Integer>> mappingResult = new ArrayList<>();

        for (String word : words) {
            Tuple<String, Integer> output = new Tuple<>();
            map(word, 1, output);
            mappingResult.add(output);
        }

        mapResult.addAll(mappingResult);
    }

    @Override
    public void map(String key, Integer value, Tuple<String, Integer> output) {
        output.key = key;
        output.value = value;
    }
}
