package org.codnect.xreflect;

import org.codnect.xreflect.binder.SimpleTypeBinder;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReflectionManagerTest {

    private ReflectionManager reflectionManager;
    private Class fooClass;

    @Before
    public void initTestObjects() {
        reflectionManager = new ReflectionManager();
        fooClass = Foo.class;
    }

    @Test
    public void testGetXClassAndToClass() {
        XClass fooXClass = reflectionManager.getXClass(Foo.class);
        assertNotNull(fooXClass);
        Class fooClass = reflectionManager.toClass(fooXClass);
        assertEquals(fooClass, Foo.class);
    }

    @Test
    public void testGetXFieldAndToField() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        XField xField = reflectionManager.getXField(field, SimpleTypeBinder.getInstance());
        assertNotNull(xField);
        Field convertedField = reflectionManager.toField(xField);
        assertEquals(field.getClass(), convertedField.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetXFieldForIllegalArgument() throws NoSuchMethodException {
        Method method = fooClass.getDeclaredMethod("getField");
        reflectionManager.getXField(method, SimpleTypeBinder.getInstance());
    }

    @Test
    public void testGetXMethodAndToMethod() throws NoSuchMethodException {
        Method method = fooClass.getDeclaredMethod("getField");
        XMethod xMethod = reflectionManager.getXMethod(method, SimpleTypeBinder.getInstance());
        assertNotNull(xMethod);
        Method convertedMethod = reflectionManager.toMethod(xMethod);
        assertEquals(method.getClass(), convertedMethod.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetXMethodForIllegalArgument() throws NoSuchFieldException {
        Field field = fooClass.getDeclaredField("field");
        reflectionManager.getXMethod(field, SimpleTypeBinder.getInstance());
    }

    @Test
    public void testGetXPropertyForFieldAndMethod() throws NoSuchFieldException, NoSuchMethodException {
        Field field = fooClass.getDeclaredField("field");
        XProperty xProperty = reflectionManager.getXProperty(field, SimpleTypeBinder.getInstance());
        assertNotNull(xProperty);
        Method method = fooClass.getDeclaredMethod("getField");
        xProperty = reflectionManager.getXProperty(method, SimpleTypeBinder.getInstance());
        assertNotNull(xProperty);
    }

    @Test
    public void testEquals() {
        XClass fooXClass = reflectionManager.getXClass(Foo.class);
        boolean result = reflectionManager.equals(fooXClass, Foo.class);
        assertTrue(result);
        result = reflectionManager.equals(fooXClass, String.class);
        assertFalse(result);
    }

}
