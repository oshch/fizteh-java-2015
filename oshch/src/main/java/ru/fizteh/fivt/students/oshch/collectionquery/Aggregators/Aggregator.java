package ru.fizteh.fivt.students.oshch.collectionquery.Aggregators;

import java.util.List;
import java.util.function.Function;

public interface Aggregator<T, R> extends Function<T, R> {
    R apply(List<T> elements);
}
