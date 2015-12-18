package ru.fizteh.fivt.students.oshch.collectionquery;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static ru.fizteh.fivt.students.oshch.collectionquery.Sources.list;
import static ru.fizteh.fivt.students.oshch.collectionquery.Sources.set;

@RunWith(MockitoJUnitRunner.class)
public class SourcesTest {
    List<Integer> resultList;
    Set<Integer> resultSet;

    @Before
    public void setUp() {
        resultList = list(0, 1, 2, 3, 4);
        resultSet = set(0, 1, 2, 3, 4);
    }

    @Test
    public void testList() {
        for (int i = 0; i < 5; i++) {
            assertTrue(resultList.contains(i));
        }
    }

    @Test
    public void testSet() {
        for (int i = 0; i < 5; i++) {
            assertTrue(resultList.contains(i));
        }
    }
}