package org.codnect.xreflect;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XClassTest {

    private ReflectionManager reflectionManager;
    private XClass fooXClass;

    @Before
    public void initTestObjects() {
        reflectionManager = new ReflectionManager();
        fooXClass = reflectionManager.getXClass(Foo.class);
    }

    @Test
    public void testGetDeclaredFieldsAndMethods() {
        assertEquals(fooXClass.getDeclaredFields().size(), 5);
        assertEquals(fooXClass.getDeclaredMethods().size(), 5);
    }

    @Test
    public void testGetDeclaredFieldAndMethodProperties() {
        assertEquals(fooXClass.getDeclaredFieldProperties().size(), 3);
        assertEquals(fooXClass.getDeclaredMethodProperties().size(), 3);
    }

    @Test
    public void testIsAssignableFrom() {
        assertTrue(fooXClass.isAssignableFrom(reflectionManager.getXClass(Foo.class)));
        assertFalse(fooXClass.isAssignableFrom(reflectionManager.getXClass(String.class)));
    }

}
