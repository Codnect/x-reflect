package org.codnect.xreflect;

import org.codnect.xreflect.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class XProperty extends XMember {

    private XProperty(Member member,
                      Type type,
                      ReflectionManager reflectionManager) {
        super(member, type, reflectionManager);
    }

    /**
     * Create a new instance of the XProperty for specified member.
     *
     * @param member an instance of the Method or Field
     * @param reflectionManager reflection manager
     * @return a new instance of the XProperty for specified member.
     */
    public static XProperty create(Member member, ReflectionManager reflectionManager) {
        if(!(member instanceof Field) && !(member instanceof Method)) {
            throw new IllegalArgumentException("The member should be a Field or Method instance for XProperty");
        }
        return new XProperty(member, null, reflectionManager);
    }

    /**
     * Get the name of the property.
     *
     * @return property name
     */
    @Override
    public String getName() {
        String propertyName = getMember().getName();

        if(getMember() instanceof Method) {
            if(propertyName.startsWith("get")) {
                return ReflectionUtil.decapitalize(propertyName);
            }
            else if(propertyName.startsWith("is")) {
                return ReflectionUtil.decapitalize(propertyName);
            }
            throw new RuntimeException("Method (" + propertyName + ") is not a property getter.");
        }

        return propertyName;
    }

    /**
     * Returns a string representation of the XProperty.
     *
     * @return a string representation of the XProperty
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Invokes the field or method.
     *
     * @param object the field or method object
     * @return if this object is a field instance, this method
     * returns the field value. Otherwise, it invokes the
     * method and returns method return value.
     */
    public Object invoke(Object object) {
        Member member = getMember();
        Object value = null;

        try{
            if(member instanceof Field) {
                Field field = (Field) member;
                Class type = field.getType();

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

            } else {
                value = ((Method)member).invoke(object, new Object[0]);
            }

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