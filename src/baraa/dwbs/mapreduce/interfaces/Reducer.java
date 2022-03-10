package baraa.dwbs.mapreduce.interfaces;

import baraa.dwbs.mapreduce.utils.Tuple;
import baraa.dwbs.mapreduce.utils.TupleGroup;

public interface Reducer<K1, V1, K2, V2> {
    void reduce(K1 key, TupleGroup<K1, V1> tupleGroup, Tuple<K2, V2> output);
}
