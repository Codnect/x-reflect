package org.codnect.xreflect.binder;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ParameterizedTypeBinder binds formal type arguments (typically T, E, etc.)
 * to actual types.
 *
 * Created by Burak Koken on 1.11.2018.
 *
 * @author Burak Koken
 */
public class ParameterizedTypeBinder implements TypeBinder {

    private Map<Type, Type> substituteTypeMap = new HashMap<>();

    /**
     * Create a new parameterized type binder according to the specified formal and
     * actual type arguments.
     *
     * @param formalTypeArguments formal type arguments
     * @param actualTypeArguments actual type arguments.
     */
    public ParameterizedTypeBinder(Type[] formalTypeArguments, Type[] actualTypeArguments) {
        for (int index = 0; index < formalTypeArguments.length; index++) {
            substituteTypeMap.put(formalTypeArguments[index], actualTypeArguments[index]);
        }
    }

    /**
     * Bind the specified type.
     *
     * @param type an instance of type
     * @return a new type for specified type.
     */
    @Override
    public Type bind(Type type) {
        if (type instanceof Class) {
            return type;
        } else if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            Type originalComponentType = genericArrayType.getGenericComponentType();
            Type boundComponentType = bind(originalComponentType);
            if(originalComponentType == boundComponentType) {
                return genericArrayType;
            }
            return TypeFactory.createArrayType(boundComponentType);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] originalArguments = parameterizedType.getActualTypeArguments();
            Type[] boundArguments = getSubstitutes(originalArguments);
            if (Arrays.equals(originalArguments, boundArguments)) {
                return parameterizedType;
            }
            return TypeFactory.createParameterizedType(parameterizedType.getRawType(),
                    boundArguments,
                    parameterizedType.getOwnerType());
        } else if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable)type;
            if (!substituteTypeMap.containsKey(typeVariable)) {
                return typeVariable;
            }
            return substituteTypeMap.get(typeVariable);
        } else if (type instanceof WildcardType) {
            return type;
        }
        return null;
    }

    /**
     * Get the substitutes for specified all types.
     *
     * @param types instances of the types
     * @return new types
     */
    private Type[] getSubstitutes(Type[] types) {
        Type[] substituteTypes = new Type[types.length];
        for (int index = 0;index < substituteTypes.length; index++) {
            substituteTypes[index] = bind(types[index]);
        }
        return substituteTypes;
    }

}
