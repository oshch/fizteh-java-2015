package ru.fizteh.fivt.students.oshch.collectionquery.Aggregators;

import java.util.List;
import java.util.function.Function;

public class Min<T, R extends Comparable<R>> implements Aggregator<T, R> {

    private Function<T, R> function;
    public Min(Function<T, R> expression) {
        this.function = expression;
    }

    @Override
    public R apply(List<T> elements) {
        return elements
                .stream()
                .map(function)
                .min(R::compareTo)
                .get();
    }

    @Override
    public R apply(T t) {
        return null;
    }
}
