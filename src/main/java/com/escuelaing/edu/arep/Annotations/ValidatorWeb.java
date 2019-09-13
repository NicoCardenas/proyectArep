package com.escuelaing.edu.arep.Annotations;

import org.reflections.*;
import org.reflections.scanners.*;

import java.lang.reflect.Method;
import java.util.*;

import com.escuelaing.edu.arep.Annotations.Annotations.Web;
import com.escuelaing.edu.arep.Framework.HttpServer;
import com.escuelaing.edu.arep.Framework.impl.MethodHandler;

public class ValidatorWeb {

    public void initializeWeb() {
        Reflections reflections = new Reflections("com.escuelaing.edu.arep.Server", new SubTypesScanner(false));
    
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        for (Class<?> clazz : allClasses) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Web.class)) {
                    HttpServer.put("apps/" + method.getAnnotation(Web.class).value(), new MethodHandler(method));
                }
            }
        }
    }
}