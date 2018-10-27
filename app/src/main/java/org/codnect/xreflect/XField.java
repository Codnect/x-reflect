package org.codnect.xreflect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class XField extends XMember {

    private XField(Member member,
                   Type type,
                   ReflectionManager reflectionManager) {
        super(member, type, reflectionManager);
    }

    /**
     * Create a new instance of the XField for specified member.
     *
     * @param member an instance of Field
     * @param reflectionManager reflection manager
     * @return a new instance of the XField for specified member.
     */
    public static XField create(Member member, ReflectionManager reflectionManager) {
        if(!(member instanceof Field)) {
            throw new IllegalArgumentException("The member should be a Field instance for XField");
        }
        /* TODO */
        return new XField(member, null, reflectionManager);
    }

    /**
     * Get the XField member name.
     *
     * @return the XField member name.
     */
    @Override
    public String getName() {
        return getMember().getName();
    }

    /**
     * Returns a string representation of the XField.
     *
     * @return a string representation of the XField
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Get the field value.
     *
     * @param object the field object
     * @return if this object is a field instance, this method
     * returns the field value.
     */
    public Object get(Object object) {
        Field field = (Field) getMember();
        Class type = field.getType();
        Object value = null;

        try {
            if(type.isPrimitive()) {
                /**
                 * Reflection API has a bug.
                 * See https://bugs.openjdk.java.net/browse/JDK-5043030 for details.
                 */
                if(type == Boolean.TYPE) {
                    return Boolean.valueOf(field.getBoolean(object));
                } else if(type == Byte.TYPE) {
                    return Byte.valueOf(field.getByte(object));
                } else if(type == Character.TYPE) {
                    return Character.valueOf(field.getChar(object));
                } else if(type == Short.TYPE) {
                    return Short.valueOf(field.getShort(object));
                } else if(type == Integer.TYPE) {
                    return Integer.valueOf(field.getInt(object));
                } else if(type == Long.TYPE) {
                    return Long.valueOf(field.getLong(object));
                }

            } else {
                value = field.get(object);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Getting " + getName() + " on a null object", e);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to get " + getName());
        }

        return value;
    }

    /**
     * Set the field value.
     *
     * @param object the field object
     * @param value field value
     */
    public void set(Object object, Object value) {
        Field field = (Field) getMember();

        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Setting " + getName() + " on a null object", e);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to set " + getName());
        }
    }

}
