package org.codnect.xreflect.binder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by Burak Koken on 1.11.2018.
 *
 * @author Burak Koken
 */
public class TypeBinderFactory {

    public TypeBinderFactory() {

    }

    /**
     * Get the type binder for specified type.
     *
     * @param type an instance of the type
     * @return type binder
     */
    public TypeBinder getBinder(Type type) {
        if(type == null) {
            return SimpleTypeBinder.getInstance();
        }
        return createBinder(type);
    }

    /**
     * Get the compound type binder according to specified type
     * and type binder.
     *
     * @param type an instance of the type
     * @param typeBinder an instance of the type binder
     * @return compound type binder
     */
    public TypeBinder getBinder(Type type, TypeBinder typeBinder) {
        return CompoundTypeBinder.create(getBinder(type), typeBinder);
    }

    /**
     * Create a new type binder for specified type.
     *
     * @param type an instance of the type
     * @return type binder
     */
    private TypeBinder createBinder(Type type) {
        if(type instanceof Class) {
            Class classType = (Class) type;
            return CompoundTypeBinder.create(createSuperTypeBinder(classType), getBinder(classType.getSuperclass()));
        } else if(type instanceof ParameterizedType) {
            return createBinder((ParameterizedType)type);
        }
        throw new IllegalArgumentException( "Invalid type for generating binder : " + type);
    }

    /**
     * Create a new type binder for specified parameterized type.
     *
     * @param type an instance of the parameterized type
     * @return type binder
     */
    private TypeBinder createBinder(ParameterizedType type) {
        Type[] actualTypeArguments = type.getActualTypeArguments();
        Type rawType = type.getRawType();
        if(rawType instanceof Class) {
            TypeVariable[] typeParameters = ((Class)rawType).getTypeParameters();
            return new ParameterizedTypeBinder(typeParameters, actualTypeArguments);
        }
        return SimpleTypeBinder.getInstance();
    }

    /**
     * Create a new type binder for specified class's super class.
     *
     * @param xClass a class
     * @return type binder
     */
    private TypeBinder createSuperTypeBinder(Class xClass) {
        Class superClass = xClass.getSuperclass();
        if(superClass == null) {
            return SimpleTypeBinder.getInstance();
        }

        Type[] formalArguments = superClass.getTypeParameters();
        Type genericSuperclass = xClass.getGenericSuperclass();
        if(genericSuperclass instanceof Class) {
            return SimpleTypeBinder.getInstance();
        } else if(genericSuperclass instanceof ParameterizedType) {
            Type[] actualArguments = ((ParameterizedType)genericSuperclass).getActualTypeArguments();
            return new ParameterizedTypeBinder(formalArguments, actualArguments);
        }

        throw new RuntimeException("Unreachable line!");
    }

}
