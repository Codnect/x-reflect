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
     *
     * @param typePair
     * @param xClass
     */
    public void addXClass(TypePair typePair, XClass xClass) {
       xClassesMap.put(typePair, xClass);
    }

    /**
     *
     * @param typePair
     * @return
     */
    public XClass getXClass(TypePair typePair) {
        return xClassesMap.get(typePair);
    }

    /**
     *
     * @param memberPair
     * @param xMethod
     */
    public void addXMethod(MemberPair memberPair, XMethod xMethod) {
        xMethodsMap.put(memberPair, xMethod);
    }

    /**
     *
     * @param memberPair
     * @return
     */
    public XMethod getXMethod(MemberPair memberPair) {
        return xMethodsMap.get(memberPair);
    }

    /**
     *
     * @param memberPair
     * @param xField
     */
    public void addXField(MemberPair memberPair, XField xField) {
        xFieldsMap.put(memberPair, xField);
    }

    /**
     *
     * @param memberPair
     * @return
     */
    public XField getXField(MemberPair memberPair) {
        return xFieldsMap.get(memberPair);
    }

    /**
     *
     * @param memberPair
     * @param xProperty
     */
    public void addXProperty(MemberPair memberPair, XProperty xProperty) {
        xPropertiesMap.put(memberPair, xProperty);
    }

    /**
     *
     * @param memberPair
     * @return
     */
    public XProperty getXProperty(MemberPair memberPair) {
        return xPropertiesMap.get(memberPair);
    }

}
