package org.codnect.xreflect.util;

import org.codnect.xreflect.Foo;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ReflectionUtilTest {

    private Class fooClass;

    @Before
    public void initTestObjects() {
        fooClass = Foo.class;
    }

    @Test
    public void testIsPropertyForFields() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("staticField");
        assertFalse(ReflectionUtil.isProperty(field));
        field = fooClass.getDeclaredField("field");
        assertTrue(ReflectionUtil.isProperty(field));
        field = fooClass.getDeclaredField("collectionField");
        assertTrue(ReflectionUtil.isProperty(field));
        field = fooClass.getDeclaredField("arrayField");
        assertTrue(ReflectionUtil.isProperty(field));
        field = fooClass.getDeclaredField("transientField");
        assertFalse(ReflectionUtil.isProperty(field));
    }

    @Test
    public void testIsPropertyForMethods() throws NoSuchMethodException {
        Method method = fooClass.getDeclaredMethod("getStaticField");
        assertFalse(ReflectionUtil.isProperty(method));
        method = fooClass.getDeclaredMethod("getField");
        assertTrue(ReflectionUtil.isProperty(method));
        method = fooClass.getDeclaredMethod("getCollectionField");
        assertTrue(ReflectionUtil.isProperty(method));
        method = fooClass.getDeclaredMethod("getArrayField");
        assertTrue(ReflectionUtil.isProperty(method));
        method = fooClass.getDeclaredMethod("voidMethod");
        assertFalse(ReflectionUtil.isProperty(method));
    }

    @Test
    public void testIsSimple() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        assertTrue(ReflectionUtil.isSimple(field.getType()));
        field = fooClass.getDeclaredField("arrayField");
        assertFalse(ReflectionUtil.isSimple(field.getType()));
        field = fooClass.getDeclaredField("collectionField");
        assertFalse(ReflectionUtil.isSimple(field.getType()));
    }

    @Test
    public void testIsArray() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        assertFalse(ReflectionUtil.isArray(field.getType()));
        field = fooClass.getDeclaredField("arrayField");
        assertTrue(ReflectionUtil.isArray(field.getType()));
        field = fooClass.getDeclaredField("collectionField");
        assertFalse(ReflectionUtil.isArray(field.getType()));
    }

    @Test
    public void testIsCollectionClass() {
        assertFalse(ReflectionUtil.isCollectionClass(String.class));
        assertTrue(ReflectionUtil.isCollectionClass(List.class));
    }

    @Test
    public void testGetCollectionClass() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        assertNull(ReflectionUtil.getCollectionClass(field.getType()));
        field = fooClass.getDeclaredField("collectionField");
        assertEquals(ReflectionUtil.getCollectionClass(field.getType()), List.class);
    }

    @Test
    public void testIsCollection() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        assertFalse(ReflectionUtil.isCollection(field.getType()));
        field = fooClass.getDeclaredField("collectionField");
        assertTrue(ReflectionUtil.isCollection(field.getType()));
    }

    @Test
    public void testDecapitalize() {
        assertEquals("unitTest", ReflectionUtil.decapitalize("UnitTest"));
    }

}
