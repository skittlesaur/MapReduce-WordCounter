package baraa.dwbs.mapreduce.utils;

public class Tuple<K, V> implements Comparable<Tuple<K, V>> {
    public K key;
    public V value;

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    @Override
    public int compareTo(Tuple<K, V> o) {
        int comp = ((Comparable<V>) o.value).compareTo(value);

        if (comp == 0)
            return ((Comparable<K>) key).compareTo(o.key);

        return comp;
    }
}
