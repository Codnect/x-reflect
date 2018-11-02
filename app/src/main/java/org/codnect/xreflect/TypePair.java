package org.codnect.xreflect;

import org.codnect.xreflect.binder.TypeBinder;
import org.codnect.xreflect.util.Pair;

import java.lang.reflect.Type;

/**
 * Created by Burak Koken on 2.11.2018.
 *
 * @author Burak Koken
 */
public class TypePair extends Pair<Type, TypeBinder> {

    public TypePair(Type key, TypeBinder value) {
        super(key, value);
    }

}
