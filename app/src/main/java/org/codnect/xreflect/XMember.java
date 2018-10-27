package org.codnect.xreflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public abstract class XMember extends XAnnotatedElement{

    private Type type;

    public XMember(Member member,
                   Type type,
                   ReflectionManager reflectionManager) {
        super((AnnotatedElement) member, reflectionManager);
        this.type = type;
    }

    /**
     * Get the member name.
     *
     * @return the member name
     */
    public abstract String getName();

    /**
     * Get the member instance.
     *
     * @return the member instance
     */
    public Member getMember() {
        return (Member) toAnnotatedElement();
    }

    /**
     * Get the modifiers for this member.
     *
     * @return the modifiers for this member.
     */
    public int getModifiers() {
        return getMember().getModifiers();
    }

    /**
     * Set the member access flag.
     *
     * @param accessible access flag
     */
    public void setAccessible(boolean accessible) {
        ((AccessibleObject)getMember()).setAccessible(accessible);
    }

}
