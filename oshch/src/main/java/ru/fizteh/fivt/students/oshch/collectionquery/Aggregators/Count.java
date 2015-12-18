package ru.fizteh.fivt.students.oshch.collectionquery.Aggregators;

import java.util.List;
import java.util.function.Function;

public class Count<T> implements Aggregator<T, Long> {

    private Function<T, ?> function;
    public Count(Function<T, ?> expression) {
        this.function = expression;
    }

    @Override
    public Long apply(List<T> elements) {
        Long longAns = elements
                .stream()
                .map(function)
                .distinct()
                .count();
        return longAns;
    }

    @Override
    public Long apply(T t) {
        return null;
    }
}
