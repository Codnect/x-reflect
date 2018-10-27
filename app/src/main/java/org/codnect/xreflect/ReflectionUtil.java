package org.codnect.xreflect;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class ReflectionUtil {

    /**
     * Decapitalizes the property name.
     *
     * @param propertyName the property name
     * @return Decapitalized property name
     */
    public static String decapitalize(String propertyName) {

        if (propertyName != null && propertyName.length() != 0) {
            char[] chars = propertyName.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }

        return propertyName;
    }

}
