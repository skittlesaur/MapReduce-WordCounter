package baraa.dwbs.mapreduce.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TupleGroup<K, V> {

    public K key;
    public List<Tuple<K, V>> tuples;

    public TupleGroup(K key) {
        this.key = key;
        this.tuples = new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TupleGroup<?, ?> that = (TupleGroup<?, ?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "TupleGroup{" +
                "key=" + key +
                ", tuples=" + tuples +
                '}';
    }
}
