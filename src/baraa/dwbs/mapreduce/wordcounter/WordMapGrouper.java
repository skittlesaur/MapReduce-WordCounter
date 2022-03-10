package baraa.dwbs.mapreduce.wordcounter;

import baraa.dwbs.mapreduce.utils.Tuple;
import baraa.dwbs.mapreduce.utils.TupleGroup;

import java.util.ArrayList;
import java.util.List;

public class WordMapGrouper {

    public static List<TupleGroup<String, Integer>> group(List<Tuple<String, Integer>> mapResults) {
        List<TupleGroup<String, Integer>> tupleGroupList = new ArrayList<>();

        for (Tuple<String, Integer> tuple : mapResults) {
            int index = tupleGroupList.indexOf(new TupleGroup<String, Integer>(tuple.key));
            if (index != -1) {
                tupleGroupList.get(index).tuples.add(tuple);
            } else {
                TupleGroup<String, Integer> tupleGroup = new TupleGroup<>(tuple.key);
                tupleGroup.tuples.add(tuple);
                tupleGroupList.add(tupleGroup);
            }
        }

        return tupleGroupList;
    }
}
