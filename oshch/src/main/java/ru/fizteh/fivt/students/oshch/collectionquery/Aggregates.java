package ru.fizteh.fivt.students.oshch.collectionquery;

import ru.fizteh.fivt.students.oshch.collectionquery.Aggregators.Avg;
import ru.fizteh.fivt.students.oshch.collectionquery.Aggregators.Count;
import ru.fizteh.fivt.students.oshch.collectionquery.Aggregators.Max;
import ru.fizteh.fivt.students.oshch.collectionquery.Aggregators.Min;

import java.util.function.Function;

public class Aggregates {
    public static <T, R extends Comparable> Function<T, R> max(Function<T, R> expression) {
        return new Max<>(expression);
    }

    public static <C, T extends Comparable<T>> Function<C, T> min(Function<C, T> expression) {
        return new Min<>(expression);
    }

    public static <T> Function<T, Long> count(Function<T, ?> expression) {
        return new Count<>(expression);
    }

    public static <T> Function<T, Double> avg(Function<T, ? extends Number> expression) {
        return new Avg<>(expression);
    }

}
