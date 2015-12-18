package ru.fizteh.fivt.students.oshch.collectionquery;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.List;

import ru.fizteh.fivt.students.oshch.collectionquery.Aggregators.Aggregator;
import ru.fizteh.fivt.students.oshch.collectionquery.CollectionsQL.Student;

import static org.junit.Assert.assertEquals;
//import static ru.fizteh.fivt.students.oshch.collectionquery.Aggregates.*;
import static ru.fizteh.fivt.students.oshch.collectionquery.CollectionsQL.Student.student;
import static ru.fizteh.fivt.students.oshch.collectionquery.Sources.list;

@RunWith(MockitoJUnitRunner.class)
public class AggregatesTest {
    List<Student> students;

    @Before
    public void setUp() {
        students = list(
                student("ivanov", LocalDate.parse("1986-08-06"), "494"),
                student("sidorov", LocalDate.parse("1986-08-06"), "495"),
                student("smith", LocalDate.parse("1986-08-06"), "495"),
                student("petrov", LocalDate.parse("2006-08-06"), "494"),
                student("petrov", LocalDate.parse("2006-08-06"), null));
    }

    @Test
    public void testMin() {
        assertEquals(Long.valueOf(9), ((Aggregator) Aggregates.min(Student::age)).apply(students));
    }

    @Test
    public void testMax() {
        assertEquals(Long.valueOf(29), ((Aggregator) Aggregates.max(Student::age)).apply(students));
    }

    @Test
    public void testCount() {
        assertEquals(Long.valueOf(3), ((Aggregator) Aggregates.count(Student::getGroup)).apply(students));
    }

    @Test
    public void testAvg() {
        assertEquals(21.0, (double) ((Aggregator) Aggregates.avg(Student::age)).apply(students), 1);
    }
}