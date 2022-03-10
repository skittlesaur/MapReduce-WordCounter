package baraa.dwbs.mapreduce.wordcounter;

import baraa.dwbs.mapreduce.utils.Tuple;
import baraa.dwbs.mapreduce.utils.TupleGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordCounter {

    public Config config;

    public WordCounter(Config config) {
        this.config = config;
    }

    public void count() throws IOException, InterruptedException {

        long startTime = System.currentTimeMillis();

        final List<Tuple<String, Integer>> mapResult = getMapResult();

        List<TupleGroup<String, Integer>> tupleGroupList = WordMapGrouper.group(mapResult);

        final List<Tuple<String, Integer>> reduceResult = getReduceResult(tupleGroupList);

        CombineResult.combine(reduceResult, config, startTime);
    }

    private List<Tuple<String, Integer>> getReduceResult(List<TupleGroup<String, Integer>> tupleGroupList) throws InterruptedException {

        final List<Tuple<String, Integer>> reduceResult = new ArrayList<>();
        final int groupsPerMachine = 1 + tupleGroupList.size() / config.machinesCount;

        int groupIndex = 0;

        for (int i = 0; i < config.machinesCount && groupIndex < tupleGroupList.size(); i++) {
            List<TupleGroup<String, Integer>> machineGroups = new ArrayList<>();
            for (int j = 0; j < groupsPerMachine; j++, groupIndex++) {
                if (groupIndex >= tupleGroupList.size())
                    break;

                machineGroups.add(tupleGroupList.get(groupIndex));
            }

            WordReducer wordReducer = new WordReducer(machineGroups, reduceResult);
            wordReducer.start();
            wordReducer.join();
        }

        return reduceResult;
    }

    private List<Tuple<String, Integer>> getMapResult() throws IOException, InterruptedException {
        List<String> lines = FileController.cleanLines(config.inputFile);
        List<String> words = FileController.splitWords(lines, config.splitRegex);

        final List<Tuple<String, Integer>> mapResult = new ArrayList<>();

        final int wordsPerMachine = 1 + words.size() / config.machinesCount;

        int wordIndex = 0;

        for (int i = 0; i < config.machinesCount && wordIndex < words.size(); i++) {
            List<String> machineWords = new ArrayList<>(wordsPerMachine);
            for (int j = 0; j < wordsPerMachine; j++, wordIndex++) {
                if (wordIndex >= words.size())
                    break;

                machineWords.add(words.get(wordIndex));
            }

            WordMapper mapper = new WordMapper(machineWords, mapResult);
            mapper.start();
            mapper.join();
        }


        return mapResult;
    }
}
