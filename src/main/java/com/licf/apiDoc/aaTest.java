package com.licf.apiDoc;

import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.licf.apiDoc.util.ADsl;
import com.licf.apiDoc.util.DocBasUtil;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.licf.bgManage.controller.BgCustomerController;
import com.licf.bgManage.entity.BgEmployee;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author licf
 * @date 2021/1/29
 */
public class aaTest {

    public static void main(String[] args) {
//        TypeDeclaration unit = getTypeD(BgCustomerController.class);

//        System.out.println(unit);

        ADsl.Builder builder = new ADsl.Builder();
        ADsl.RpcStruct type1 = builder.type(BgEmployee.class);
        System.out.println(type1);

    }

    static CompilationUnit getUnit(Class type) {

        try {
            String path = DocBasUtil.sourcePath(type);
            InputStream in = new FileInputStream(path + type.getName().replace('.', '/') + ".java");
            if (in == null) {
                return null;
            }
            return JavaParser.parse(in);

        } catch (Exception e) {
            return null;
        }


    }
    static TypeDeclaration getTypeD(Class type) {
        CompilationUnit unit = getUnit(type);
        if (unit != null) {
            List<TypeDeclaration> typeDs = unit.getTypes();
            if (typeDs != null) {
                String name = type.getSimpleName();
                for (TypeDeclaration typeD : typeDs) {
                    TypeDeclaration d = getTypeD("", typeD, type);
                    if (d != null) {
                        return d;
                    }
                }
            }
        }

        return null;
    }
    static TypeDeclaration getTypeD(String name, TypeDeclaration typeD, Class type) {
        if (typeD != null) {
            name = name + typeD.getName();
            if (type.getName().endsWith(name) && (type.getName().length() == name.length() || type.getName().charAt(type.getName().length() - name.length() - 1) == '.')) {
                return typeD;
            }

            name = name + '$';
            if (type.getName().indexOf(name) >= 0) {
                for (BodyDeclaration member : typeD.getMembers()) {
                    if (member instanceof TypeDeclaration) {
                        TypeDeclaration d = getTypeD(name, (TypeDeclaration) member, type);
                        if (d != null) {
                            return d;
                        }
                    }
                }
            }
        }

        return null;
    }
}
