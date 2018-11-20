package org.codnect.xreflect;

import org.codnect.xreflect.binder.SimpleTypeBinder;
import org.codnect.xreflect.binder.TypeBinder;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class XCollectionTypeTest {

    private ReflectionManager reflectionManager;
    private XType xType;

    @Before
    public void initTestObjects() {
        reflectionManager = new ReflectionManager();
        XClass fooXClass = reflectionManager.getXClass(Foo.class);
        XField xField = fooXClass.getDeclaredField("collectionField");
        TypeBinder typeBinder = SimpleTypeBinder.getInstance();
        Type type = typeBinder.bind((reflectionManager.toField(xField)).getGenericType());
        xType = reflectionManager.getXType(typeBinder, type);
    }

    @Test
    public void testIsCollection() {
        assertTrue(xType.isCollection());
    }

    @Test
    public void testIsArray() {
        assertFalse(xType.isArray());
    }

    @Test
    public void testGetType() {
        assertTrue(reflectionManager.equals(xType.getType(), List.class));
    }

    @Test
    public void testGetElementClass() {
        assertTrue(reflectionManager.equals(xType.getElementClass(), String.class));
    }

    @Test
    public void testGetCollectionClass() {
        assertEquals(xType.getCollectionClass(), List.class);
    }

    @Test
    public void testGetMapKey() {
        assertNull(xType.getMapKey());
    }

    @Test
    public void testGetClassOrElementClass() {
        assertTrue(reflectionManager.equals(xType.getClassOrElementClass(), List.class));
    }

}
