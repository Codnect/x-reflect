package org.codnect.xreflect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Burak Koken on 3.11.2018.
 *
 * @author Burak Koken
 */
public class ReflectionCollector {

    private Map<TypePair, XClass> xClassesMap;
    private Map<MemberPair, XMethod> xMethodsMap;
    private Map<MemberPair, XField> xFieldsMap;
    private Map<MemberPair, XProperty> xPropertiesMap;

    protected ReflectionCollector() {
        xClassesMap = new HashMap<>();
        xMethodsMap = new HashMap<>();
        xFieldsMap = new HashMap<>();
        xPropertiesMap = new HashMap<>();
    }

    /**
     * Add the converted XClass.
     *
     * @param typePair a type pair
     * @param xClass an instance of XClass
     */
    public void addXClass(TypePair typePair, XClass xClass) {
       xClassesMap.put(typePair, xClass);
    }

    /**
     * Get the instance of the XClass for specified type pair.
     *
     * @param typePair a type pair
     * @return the instance of the XClass for specified type pair
     */
    public XClass getXClass(TypePair typePair) {
        return xClassesMap.get(typePair);
    }

    /**
     * Add the converted XMethod.
     *
     * @param memberPair a member pair
     * @param xMethod an instance of XMethod
     */
    public void addXMethod(MemberPair memberPair, XMethod xMethod) {
        xMethodsMap.put(memberPair, xMethod);
    }

    /**
     * Get the instance of the XMethod for specified member pair.
     *
     * @param memberPair a member pair
     * @return the instance of the XMethod for specified member pair
     */
    public XMethod getXMethod(MemberPair memberPair) {
        return xMethodsMap.get(memberPair);
    }

    /**
     * Add the converted XField.
     *
     * @param memberPair a member pair
     * @param xField an instance of XField
     */
    public void addXField(MemberPair memberPair, XField xField) {
        xFieldsMap.put(memberPair, xField);
    }

    /**
     * Get the instance of the XField for specified member pair.
     *
     * @param memberPair a member pair
     * @return the instance of the XField for specified member pair
     */
    public XField getXField(MemberPair memberPair) {
        return xFieldsMap.get(memberPair);
    }

    /**
     * Add the converted XProperty.
     *
     * @param memberPair a member pair
     * @param xProperty an instance of XProperty
     */
    public void addXProperty(MemberPair memberPair, XProperty xProperty) {
        xPropertiesMap.put(memberPair, xProperty);
    }

    /**
     * Get the instance of the XProperty for specified member pair.
     *
     * @param memberPair a member pair
     * @return the instance of the XProperty for specified member pair
     */
    public XProperty getXProperty(MemberPair memberPair) {
        return xPropertiesMap.get(memberPair);
    }

}
