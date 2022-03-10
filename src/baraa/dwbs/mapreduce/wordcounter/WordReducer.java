package baraa.dwbs.mapreduce.wordcounter;

import baraa.dwbs.mapreduce.interfaces.Reducer;
import baraa.dwbs.mapreduce.utils.Tuple;
import baraa.dwbs.mapreduce.utils.TupleGroup;

import java.util.ArrayList;
import java.util.List;

public class WordReducer extends Thread implements Reducer<String, Integer, String, Integer> {

    private final List<TupleGroup<String, Integer>> groupList;
    private final List<Tuple<String, Integer>> reducerResult;

    public WordReducer(List<TupleGroup<String, Integer>> groupList, List<Tuple<String, Integer>> reducerResult) {
        this.groupList = groupList;
        this.reducerResult = reducerResult;
    }

    @Override
    public void run() {

        List<Tuple<String, Integer>> outputList = new ArrayList<>();

        for (TupleGroup<String, Integer> tupleGroup : groupList) {
            Tuple<String, Integer> output = new Tuple<>();
            reduce(tupleGroup.key, tupleGroup, output);
            outputList.add(output);
        }

        reducerResult.addAll(outputList);
    }

    @Override
    public void reduce(String key, TupleGroup<String, Integer> tupleGroup, Tuple<String, Integer> output) {
        output.key = key;
        output.value = tupleGroup.tuples.size();
    }
}
