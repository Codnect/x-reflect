package org.codnect.xreflect;

import org.codnect.xreflect.binder.CompoundTypeBinder;
import org.codnect.xreflect.binder.TypeBinder;
import org.codnect.xreflect.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class XClass extends XAnnotatedElement {

    private Class annotatedClass;
    private TypeBinder typeBinder;

    protected XClass(Class annotatedClass, ReflectionManager reflectionManager, TypeBinder typeBinder) {
        super(annotatedClass, reflectionManager);
        this.annotatedClass = annotatedClass;
        this.typeBinder = typeBinder;
    }

    /**
     * Get this annotated class name.
     *
     * @return this annotated class name
     */
    public String getName() {
        return annotatedClass.getName();
    }

    /**
     * Gets the modifiers for this element.
     *
     * @return the modifiers for this element.
     */
    public int getModifiers() {
        return annotatedClass.getModifiers();
    }

    /**
     * Determines if this element is an primitive type.
     *
     * @return if this element is an primitive type, it returns
     * true. Otherwise it returns false.
     */
    public boolean isPrimitive() {
        return annotatedClass.isPrimitive();
    }

    /**
     * Determines if this element is an enum.
     *
     * @return if this element is an enum, it returns
     * true. Otherwise it returns false.
     */
    public boolean isEnum() {
        return annotatedClass.isEnum();
    }

    /**
     * Determines if this element is an interface.
     *
     * @return if this element is an interface, it returns
     * true. Otherwise it returns false.
     */
    public boolean isInterface() {
        return annotatedClass.isInterface();
    }

    /**
     * Determines if this element is an array.
     *
     * @return if this element is an array, it returns
     * true. Otherwise it returns false.
     */
    boolean isArray() {
        return annotatedClass.isArray();
    }

    /**
     * Determines if this element is an abstract class.
     *
     * @return if this element is an abstract class, it returns
     * true. Otherwise it returns false.
     */
    public boolean isAbstract() {
        return Modifier.isAbstract(annotatedClass.getModifiers());
    }

    /**
     * Determines if the class or interface represented by this XClass
     * is either the same as, or is a superclass or  super interface of,
     * the class or interface represented by the specified parameter.
     *
     * @param xClass another XClass
     * @return result
     */
    public boolean isAssignableFrom(XClass xClass) {
        return annotatedClass.isAssignableFrom(xClass.toClass());
    }

    /**
     * Get all the declared fields for this element.
     *
     * @return all the declared fields
     */
    public List<XField> getDeclaredFields() {
        List<XField> fields = new LinkedList<>();
        for(Field field : annotatedClass.getDeclaredFields()) {
            fields.add(getReflectionManager().getXField(field, getTypeBinder()));
        }
        return fields;
    }

    /**
     * Get the declared field for specified field name.
     *
     * @param fieldName field name
     * @return the declared field for specified field name.
     *         if it does not exist, returns null.
     */
    public XField getDeclaredField(String fieldName) {
        Field field;
        try {
            field = annotatedClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
        return getReflectionManager().getXField(field, getTypeBinder());
    }

    /**
     * Get all the declared methods for this element.
     *
     * @return all the declared methods
     */
    public List<XMethod> getDeclaredMethods() {
        List<XMethod> methods = new LinkedList<>();
        for(Method method : annotatedClass.getDeclaredMethods()) {
            methods.add(getReflectionManager().getXMethod(method, getTypeBinder()));
        }
        return methods;
    }

    /**
     * Get the declared method for specified method name and
     * parameter types.
     *
     * @param methodName method name
     * @param parameterTypes method's parameter types
     * @return the declared method for specified method name and
     *         parameter types. if it does not exist, returns null.
     */
    public XMethod getDeclaredMethod(String methodName, Class... parameterTypes) {
        Method method;
        try {
            method = annotatedClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
        return getReflectionManager().getXMethod(method, getTypeBinder());
    }

    /**
     * Get all the declared field properties for this element.
     *
     * @return all the declared field properties
     */
    public List<XProperty> getDeclaredFieldProperties() {
        LinkedList<XProperty> fieldProperties = new LinkedList<>();
        for(Field field : annotatedClass.getDeclaredFields()) {
            if(ReflectionUtil.isProperty(field)) {
                fieldProperties.add(getReflectionManager().getXProperty(field, getTypeBinder()));
            }
        }
        return fieldProperties;
    }

    /**
     * Get the declared field property for specified field name.
     *
     * @param fieldName field name
     * @return the declared field property for specified field name.
     *         if it does not exist, returns null.
     */
    public XField getDeclaredFieldProperty(String fieldName) {
        Field field;
        try {
            field = annotatedClass.getDeclaredField(fieldName);
            if(!ReflectionUtil.isProperty(field)) {
                return null;
            }
        } catch (NoSuchFieldException e) {
            return null;
        }
        return getReflectionManager().getXField(field, getTypeBinder());
    }

    /**
     * Get all the declared method properties for this element.
     *
     * @return all the declared method properties
     */
    public List<XProperty> getDeclaredMethodProperties() {
        LinkedList<XProperty> methodProperties = new LinkedList<>();
        for(Method method : annotatedClass.getDeclaredMethods()) {
            if(ReflectionUtil.isProperty(method)) {
                methodProperties.add(getReflectionManager().getXProperty(method, getTypeBinder()));
            }
        }
        return methodProperties;
    }

    /**
     * Get the declared method property for specified method name
     * and parameter types.
     *
     * @param methodName method name
     * @param parameterTypes method's parameter types
     * @return the declared method property for specified method name
     *         and parameter types. if it does not exist, returns null.
     */
    public XMethod getDeclaredMethodProperty(String methodName, Class... parameterTypes) {
        Method method;
        try {
            method = annotatedClass.getDeclaredMethod(methodName, parameterTypes);
            if(!ReflectionUtil.isProperty(method)) {
                return null;
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
        return getReflectionManager().getXMethod(method, getTypeBinder());
    }

    /**
     * Get the type binder for this XClass.
     *
     * @return the type binder for this XClass
     */
    public TypeBinder getTypeBinder() {
        return typeBinder;
    }

    /**
     * Get the this XClass's super class.
     *
     * @return the this XClass's super class
     */
    public XClass getSuperclass() {
        return getReflectionManager().getXClass(annotatedClass.getSuperclass(),
                CompoundTypeBinder.create(
                        getTypeBinder(),
                        getReflectionManager().getTypeBinder(annotatedClass)
                )
        );
    }

    /**
     * Get the interfaces for XClass.
     *
     * @return the interfaces for XClass
     */
    public XClass[] getInterfaces() {
        Class[] classes = toClass().getInterfaces();
        int interfaceCount = classes.length;
        XClass[] xClasses = new XClass[interfaceCount];
        if(interfaceCount != 0) {
            TypeBinder typeBinder = CompoundTypeBinder.create(
                    getTypeBinder(),
                    getReflectionManager().getTypeBinder(annotatedClass)
            );
            for(int index = 0;index < interfaceCount;index++) {
                xClasses[index] = getReflectionManager().getXClass(classes[index], typeBinder);
            }
        }
        return xClasses;
    }

    /**
     * Converts a XClass to a Class.
     *
     * @return a Class
     */
    public Class toClass() {
        return annotatedClass;
    }

    /**
     * Returns a string representation of the XClass.
     *
     * @return a string representation of the XClass
     */
    @Override
    public String toString() {
        return annotatedClass.getName();
    }

}