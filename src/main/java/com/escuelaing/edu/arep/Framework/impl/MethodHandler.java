package com.escuelaing.edu.arep.Framework.impl;

import com.escuelaing.edu.arep.Framework.Handler;

public class StaticMethodHandler implements Handler {
    Method method;

    public StaticMethodHandler(Method method) {
        this.method = method;
    }

    @Override
    public String process() {
        try {
            return (String) this.method.invoke(null, null);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    @Override
    public String process(String[] params) {
        try {
            return (String)this.method.invoke(null, params);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
        return null;
    }
}