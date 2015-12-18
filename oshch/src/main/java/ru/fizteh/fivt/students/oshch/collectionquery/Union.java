package ru.fizteh.fivt.students.oshch.collectionquery;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Union<T, R> {
    private List<Select<T, R>> selects = new ArrayList<>();
    private Select<T, R> current;
    private From<T> curFrom;
    public Union(Select<T, R> initSelect) {
        selects.add(initSelect);
    }

    public Union<T, R> from(Iterable<T> iterable) {
        curFrom = new From(iterable);
        return this;
    }

    public Union<T, R> select(Class<R> resultClass,
                                  Function<T, ?>... constructorFunctions)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        current = curFrom.select(resultClass, constructorFunctions);
        return this;
    }

    public List<R> execute() throws NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException {
        List<R> result = new ArrayList<>();
        selects.add(current);
        for (Select<T, R> select : selects) {
            result.addAll(select.execute());
        }
        return result;
    }
}
