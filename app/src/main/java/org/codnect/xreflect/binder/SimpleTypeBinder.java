package org.codnect.xreflect.binder;

import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 28.10.2018.
 *
 * @author Burak Koken
 */
public class SimpleTypeBinder implements TypeBinder {

    private static final SimpleTypeBinder simpleTypeBinder = new SimpleTypeBinder();

    private SimpleTypeBinder() {

    }

    /**
     * Get the instance of the SimpleTypeBinder.
     *
     * @return the instance of the SimpleTypeBinder
     */
    public static SimpleTypeBinder getInstance() {
        return simpleTypeBinder;
    }

    /**
     * Bind the specified type.
     *
     * @param type an instance of type
     * @return a new type for specified type.
     */
    @Override
    public Type bind(Type type) {
        return type;
    }

}