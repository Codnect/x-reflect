package org.codnect.xreflect;

import java.util.List;

public class Foo {

    static int staticField;
    String field;
    List<String> collectionField;
    String[] arrayField;
    transient int transientField;

    public static int getStaticField() {
        return 0;
    }

    public String getField() {
        return null;
    }

    public List<String> getCollectionField() {
        return null;
    }

    public String[] getArrayField() {
        return null;
    }

    public void voidMethod() {

    }

}
