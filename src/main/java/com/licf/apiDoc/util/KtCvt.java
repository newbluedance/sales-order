package com.licf.apiDoc.util;


import java.lang.reflect.TypeVariable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 *
 * @author:absir
 * @Date:20/11/2018
 * @Time:7:21 PM
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class KtCvt {

    private static DateFormat dateFmt;

    private static DateFormat dayFmt;

    private static DateFormat timeFmt;

    private static List<DateFormat> fmtList;
    public static TypeVariable ListT = Collection.class.getTypeParameters()[0];

    public static TypeVariable MapK = Map.class.getTypeParameters()[0];

    public static TypeVariable MapV = Map.class.getTypeParameters()[1];

    public static Class ot(Class type) {
        if (type == null) {
            return null;
        }

        if (intType(type)) {
            return Integer.class;

        } else if (longType(type)) {
            return Long.class;

        } else if (boolType(type)) {
            return Boolean.class;

        } else if (floatType(type)) {
            return Float.class;

        } else if (doubleType(type)) {
            return Double.class;

        } else if (byteType(type)) {
            return Byte.class;

        } else if (charType(type)) {
            return Character.class;

        } else if (shortType(type)) {
            return Short.class;
        }

        return type;
    }

    public static boolean as(Class type, Class toType) {
        if (toType == null) {
            return true;
        }

        if (type == null) {
            return false;
        }

        if (toType.isAssignableFrom(type)) {
            return true;

        } else if (intType(type)) {
            return intType(toType);

        } else if (longType(type)) {
            return longType(toType);

        } else if (boolType(type)) {
            return boolType(toType);

        } else if (floatType(type)) {
            return floatType(toType);

        } else if (doubleType(type)) {
            return doubleType(toType);

        } else if (byteType(type)) {
            return byteType(toType);

        } else if (charType(type)) {
            return charType(toType);

        } else if (shortType(type)) {
            return shortType(toType);
        }

        return false;
    }

    static boolean intType(Class type) {
        return type == int.class || type == Integer.class;
    }

    static boolean longType(Class type) {
        return type == long.class || type == Long.class;
    }

    static boolean boolType(Class type) {
        return type == boolean.class || type == Boolean.class;
    }

    static boolean floatType(Class type) {
        return type == float.class || type == Float.class;
    }

    static boolean doubleType(Class type) {
        return type == double.class || type == Double.class;
    }

    static boolean byteType(Class type) {
        return type == byte.class || type == Byte.class;
    }

    static boolean charType(Class type) {
        return type == char.class || type == Character.class;
    }

    static boolean shortType(Class type) {
        return type == short.class || type == Short.class;
    }
}
