package org.codnect.xreflect;

import java.lang.reflect.Modifier;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class XClass extends XAnnotatedElement {

    private Class annotatedClass;

    protected XClass(Class annotatedClass, ReflectionManager reflectionManager) {
        super(annotatedClass, reflectionManager);
        this.annotatedClass = annotatedClass;
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