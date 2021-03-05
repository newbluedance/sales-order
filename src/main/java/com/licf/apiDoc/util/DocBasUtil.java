package com.licf.apiDoc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author licf
 * @date 2021/1/29
 */
public class DocBasUtil {

    public static String sourcePath(Class cls) {
        String classPath = classPath(cls);
        return classPath.replace("target/classes/","src/main/java/") ;
    }

    public static String classPath(Class cls) {
        ProtectionDomain domain = cls.getProtectionDomain();
        if (domain != null) {
            CodeSource source = domain.getCodeSource();
            if (source != null) {
                String file = uPath(source.getLocation().getFile());
                return file;
            }
        }

        return uPath(cls.getResource("").getFile());
    }

    public static String uPath(String uPath) {
        if (uPath != null && uPath.indexOf('%') >= 0) {
            try {
                return URLDecoder.decode(uPath, "UTF-8");

            } catch (UnsupportedEncodingException e) {
            }
        }

        return uPath;
    }
}
