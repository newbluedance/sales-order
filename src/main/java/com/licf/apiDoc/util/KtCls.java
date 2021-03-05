package com.licf.apiDoc.util;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Map;

/**
 * Created with IDEA
 *
 * @author:absir
 * @Date:6/12/2018
 * @Time:8:30 AM
 */
public class KtCls {

    public static Class raw(Type type) {
        if (type == null) {
            return null;
        }

        if (type instanceof Class) {
            return (Class) type;

        } else if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();

        } else if (type instanceof GenericArrayType) {
            try {
                return Array.newInstance(raw(((GenericArrayType) type).getGenericComponentType()), 0).getClass();

            } catch (Exception e) {
            }
        }

        return Object.class;
    }

    public static Class rawVar(Type type, TypeVariable tVar) {
        return raw(typeVar(type, tVar));
    }

    public static Type typeVar(Type type, TypeVariable tVar) {
        Type tV = typeVarIn(type, tVar);
        while (tV != tVar && tV instanceof TypeVariable) {
            tVar = (TypeVariable) tV;
            tV = typeVarIn(type, tVar);
        }

        return tV;
    }

    static Type typeVarIn(Type type, TypeVariable tVar) {
        GenericDeclaration varD = tVar.getGenericDeclaration();
        Class varC = varD instanceof Type ? KtCls.raw((Type) varD) : null;
        if (varC == null) {
            return tVar;
        }

        Class sClass = raw(type);
        if (!varC.isAssignableFrom(sClass)) {
            return tVar;
        }

        Type sType = type;
        boolean varI = varC.isInterface();
        while (true) {
            if (sClass == varC) {
                if (sType instanceof ParameterizedType) {
                    int i = -1;
                    for (Type var : varD.getTypeParameters()) {
                        i++;
                        if (var == tVar) {
                            return ((ParameterizedType) sType).getActualTypeArguments()[i];
                        }
                    }
                }
            }

            if (varI) {
                for (Type iType : sClass.getGenericInterfaces()) {
                    Type tV = typeVarIn(iType, tVar);
                    if (tV != tVar) {
                        return tV;
                    }
                }
            }

            sType = sClass.getGenericSuperclass();
            sClass = sClass.getSuperclass();
            if (sClass == null || sClass == Object.class) {
                break;
            }

            if (!varC.isAssignableFrom(sClass)) {
                break;
            }
        }

        return tVar;
    }

    public static Type typeGen(Type type, Type tGen) {
        if (tGen instanceof TypeVariable) {
            return typeVar(type, (TypeVariable) tGen);
        }

        return tGen;
    }

    public static Type[] typeGens(Type type, Type[] tGens) {
        if (tGens == null) {
            return null;
        }

        for (int i = tGens.length - 1; i >= 0; i--) {
            tGens[i] = typeGen(type, tGens[i]);
        }

        return tGens;
    }

    public static Class forName(String className) {
        try {
            return Class.forName(className);

        } catch (ClassNotFoundException e) {
        }

        return null;
    }

    public static boolean noEntity(Class cls) {
        return (cls == null
                || cls.isEnum()
                || cls.isArray()
                || Collection.class.isAssignableFrom(cls)
                || Map.class.isAssignableFrom(cls));
    }



}
