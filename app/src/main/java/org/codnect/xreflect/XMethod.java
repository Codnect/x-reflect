package org.codnect.xreflect;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class XMethod extends XMember {

    private XMethod(Member member,
                    Type type,
                    ReflectionManager reflectionManager) {
        super(member, type, reflectionManager);
    }

    /**
     * Create a new instance of the XMethod for specified member.
     *
     * @param member an instance of Method
     * @param reflectionManager reflection manager
     * @return a new instance of the XMethod for specified member.
     */
    public static XMethod create(Member member, ReflectionManager reflectionManager) {
        if(!(member instanceof Method)) {
            throw new IllegalArgumentException("The member should be a Method instance for XMethod");
        }
        return new XMethod(member, null, reflectionManager);
    }

    /**
     * Get the XMethod member name.
     *
     * @return the XMethod member name.
     */
    @Override
    public String getName() {
        return getMember().getName();
    }

    /**
     * Returns a string representation of the XMethod.
     *
     * @return a string representation of the XMethod
     */
    @Override
    public String toString() {
        return getName();
    }


    /**
     * Invokes the method.
     *
     * @param object the method object
     * @return if this object is a method instance, it
     * invokes the method and returns method return value.
     */
    public Object invoke(Object object) {
        Object value;

        try {
            value = ((Method)getMember()).invoke(object, new Object[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invoking " + getName() + " on a null object", e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invoking " + getName() + " with wrong parameters", e );
        } catch (Exception e) {
            throw new IllegalStateException("Unable to invoke " + getName(), e);
        }

        return value;
    }

    /**
     * Invokes the method.
     *
     * @param object the method object
     * @param parameters parameters to pass
     * @return if this object is a method instance, it
     * invokes the method and returns method return value.
     */
    public Object invoke(Object object, Object... parameters) {
        Object value = null;

        try {
            value = ((Method)getMember()).invoke(object, parameters);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invoking " + getName() + " on a null object", e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invoking " + getName() + " with wrong parameters", e );
        } catch (Exception e) {
            throw new IllegalStateException("Unable to invoke " + getName(), e);
        }

        return value;
    }

}