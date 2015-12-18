package ru.fizteh.fivt.students.oshch.collectionquery;

import java.util.*;

public class Sources {

    @SafeVarargs
    public static <T> List<T> list(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @SafeVarargs
    public static <T> Set<T> set(T... items) {
        Set<T> result = new HashSet<>();
        result.addAll(list(items));
        return result;
    }

}
