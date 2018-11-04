package org.codnect.xreflect;

import org.codnect.xreflect.binder.TypeBinder;
import org.codnect.xreflect.util.Pair;

import java.lang.reflect.Member;

/**
 * Created by Burak Koken on 2.11.2018.
 *
 * @author Burak Koken
 */
public class MemberPair extends Pair<Member, TypeBinder> {

    protected MemberPair(Member key, TypeBinder value) {
        super(key, value);
    }

}
