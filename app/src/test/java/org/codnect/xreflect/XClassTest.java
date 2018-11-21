package org.codnect.xreflect;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
        assertEquals(fooXClass.getDeclaredFields().size(), 6);
        assertEquals(fooXClass.getDeclaredMethods().size(), 6);
    }

    @Test
    public void testGetDeclaredFieldAndMethod() {
        assertNotNull(fooXClass.getDeclaredField("field"));
        assertNull(fooXClass.getDeclaredField("unknownField"));
        assertNotNull(fooXClass.getDeclaredMethod("getField"));
        assertNull(fooXClass.getDeclaredMethod("unknownGetField"));
    }

    @Test
    public void testGetDeclaredFieldAndMethodProperties() {
        assertEquals(fooXClass.getDeclaredFieldProperties().size(), 4);
        assertEquals(fooXClass.getDeclaredMethodProperties().size(), 4);
    }

    @Test
    public void testGetDeclaredFieldAndMethodProperty() {
        assertNotNull(fooXClass.getDeclaredFieldProperty("field"));
        assertNull(fooXClass.getDeclaredFieldProperty("transientField"));
        assertNotNull(fooXClass.getDeclaredMethodProperty("getField"));
        assertNull(fooXClass.getDeclaredMethodProperty("getStaticField"));
    }

    @Test
    public void testIsAssignableFrom() {
        assertTrue(fooXClass.isAssignableFrom(reflectionManager.getXClass(Foo.class)));
        assertFalse(fooXClass.isAssignableFrom(reflectionManager.getXClass(String.class)));
    }

}
