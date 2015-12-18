package ru.fizteh.fivt.students.oshch.collectionquery;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static ru.fizteh.fivt.students.oshch.collectionquery.CollectionsQL.Student.student;
import static ru.fizteh.fivt.students.oshch.collectionquery.Conditions.rlike;

@RunWith(MockitoJUnitRunner.class)
public class ConditionsTest extends TestCase {
    Function<CollectionsQL.Student, String> function = CollectionsQL.Student::getName;

    @Test
    public void testRlike() throws Exception {
        List<CollectionsQL.Student> exampleList = new ArrayList<>();
        exampleList.add(student("ivanushka", LocalDate.parse("1986-08-06"), "494"));
        exampleList.add(student("alena", LocalDate.parse("1986-08-06"), "495"));
        exampleList.add(student("ivanov", LocalDate.parse("1986-08-06"), "495"));

        assertEquals(rlike(function, ".*ov").test(exampleList.get(0)), false);
        assertEquals(rlike(function, ".*ov").test(exampleList.get(1)), false);
        assertEquals(rlike(function, ".*ov").test(exampleList.get(2)), true);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLike() throws Exception {
        Conditions.like(function, "aaa");
    }
}