package ru.fizteh.fivt.students.oshch.collectionquery;


import org.mockito.internal.util.collections.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Sources {

    @SafeVarargs
    public static <T> List<T> list(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @SafeVarargs
    public static <T> Set<T> set(T... items) {
        return Sets.newSet(items);
    }

}
