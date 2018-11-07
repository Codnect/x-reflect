package org.codnect.xreflect;

import org.codnect.xreflect.binder.SimpleTypeBinder;
import org.codnect.xreflect.binder.TypeBinder;
import org.codnect.xreflect.binder.TypeBinderFactory;
import org.codnect.xreflect.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 27.10.2018.
 *
 * @author Burak Koken
 */
public class ReflectionManager {

    private TypeBinderFactory typeBinderFactory;
    private ReflectionCollector reflectionCollector;

    public ReflectionManager() {
        typeBinderFactory = new TypeBinderFactory();
        reflectionCollector = new ReflectionCollector();
    }

    /**
     * Get the instance of the XClass for specified annotated
     * class.
     *
     * @param annotatedClass annotated class
     * @return the instance of the XClass for specified annotated
     * class
     */
    public XClass getXClass(Class annotatedClass) {
        return getXClass(annotatedClass, SimpleTypeBinder.getInstance());
    }

    /**
     * Get the instance of the XClass for specified type.
     *
     * @param type a type that will be converted to an instance
     *        of XClass.
     * @param typeBinder a type binder
     * @return the instance of the XClass for specified type
     */
    protected XClass getXClass(Type type, TypeBinder typeBinder) {
        Type boundType = typeBinder.bind(type);
        if(boundType instanceof Class) {
            TypePair typePairKey = new TypePair(boundType, typeBinder);
            XClass xClass = reflectionCollector.getXClass(typePairKey);
            if(xClass == null) {
                xClass = new XClass((Class) boundType, this, typeBinder);
                reflectionCollector.addXClass(typePairKey, xClass);
            }
            return xClass;
        }
        else if(boundType instanceof ParameterizedType) {
            getXClass(((ParameterizedType)boundType).getRawType(),
                    typeBinderFactory.getBinder(boundType, typeBinder));
        }
        throw new RuntimeException("This type cannot be converted to a XClass : " + type.toString());
    }

    /**
     * Converts a XClass to a Class.
     *
     * @param xClass an instance of the XClass
     * @return a Class
     */
    public Class toClass(XClass xClass) {
        return (Class) xClass.toAnnotatedElement();
    }

    /**
     * Get the instance of the XMethod for specified Field
     * class member.
     *
     * @param member an instance of the Field
     * @param typeBinder a type binder
     * @return  the instance of the XField for Field
     */
    public XField getXField(Member member, TypeBinder typeBinder) {
        MemberPair memberPairKey = new MemberPair(member, typeBinder);
        XField xField = reflectionCollector.getXField(memberPairKey);
        if(xField != null) {
            return xField;
        }
        xField = XField.create(member, typeBinder, this);
        reflectionCollector.addXField(memberPairKey, xField);
        return xField;
    }

    /**
     * Converts a XField to a Field.
     *
     * @param xField an instance of the XField
     * @return a Field
     */
    public Field toField(XField xField) {
        return (Field) xField.toAnnotatedElement();
    }

    /**
     * Get the instance of the XMethod for specified Method
     * class member.
     *
     * @param member an instance of the Method
     * @param typeBinder a type binder
     * @return the instance of the XMethod for Method
     */
    public XMethod getXMethod(Member member, TypeBinder typeBinder) {
        MemberPair memberPairKey = new MemberPair(member, typeBinder);
        XMethod xMethod = reflectionCollector.getXMethod(memberPairKey);
        if(xMethod != null) {
            return xMethod;
        }
        xMethod = XMethod.create(member, typeBinder, this);
        reflectionCollector.addXMethod(memberPairKey, xMethod);
        return xMethod;
    }

    /**
     * Converts a XMethod to a Method.
     *
     * @param xMethod an instance of the XMethod
     * @return a Method
     */
    public Method toMethod(XMethod xMethod) {
        return (Method) xMethod.toAnnotatedElement();
    }

    /**
     * Get the instance of the XProperty for specified field or
     * method class member.
     *
     * @param member an instance of the Field or the Method
     * @param typeBinder a type binder
     * @return the instance of the XProperty for Field or Method
     */
    public XProperty getXProperty(Member member, TypeBinder typeBinder) {
        MemberPair memberPairKey = new MemberPair(member, typeBinder);
        XProperty xProperty = reflectionCollector.getXProperty(memberPairKey);
        if(xProperty != null) {
            return xProperty;
        }
        xProperty = XProperty.create(member, typeBinder, this);
        reflectionCollector.addXProperty(memberPairKey, xProperty);
        return xProperty;
    }

    /**
     * Get the instance of the XType for specified type.
     *
     * @param typeBinder an instance of the type binder
     * @param type an instance of the type
     * @return the instance of the XType for specified type.
     */
    public XType getXType(TypeBinder typeBinder, Type type) {
        Type boundType = toApproximateBinder(typeBinder).bind(type);
        if(ReflectionUtil.isSimple(boundType)) {
            return new XSimpleType(type, typeBinder, this);
        } else if(ReflectionUtil.isArray(boundType)) {
            return new XArrayType(type, typeBinder, this);
        } else if(ReflectionUtil.isCollection(boundType)) {
            return new XCollectionType(type, typeBinder, this);
        }

        throw new IllegalArgumentException(type + " type cannot be convert to XType");
    }

    /**
     * Converts the specified type binder to an approximate type
     * binder.
     *
     * @param typeBinder an instance of the type binder
     * @return an approximate type binder
     */
    public TypeBinder toApproximateBinder(TypeBinder typeBinder) {
        return typeBinderFactory.toApproximateBinder(typeBinder);
    }

    /**
     * Get the type binder for specified type.
     *
     * @param type an instance of the type
     * @return a type binder for specified type
     */
    public TypeBinder getTypeBinder(Type type) {
        if(type instanceof Class || type instanceof ParameterizedType) {
            return typeBinderFactory.getBinder(type);
        }
        return null;
    }

    /**
     * Convert the specified Class to XClass, then compare the
     * converted class and specified XClass. if they are same,
     * it returns true. Otherwise, it returns false.
     *
     * @param xClass a XClass
     * @param otherClass a Class
     * @return if they are same, it returns true. Otherwise,
     * it returns false.
     */
    public boolean equals(XClass xClass, Class otherClass) {
        if (xClass == null) {
            return otherClass == null;
        }
        return xClass.toClass().equals(otherClass);
    }

}
