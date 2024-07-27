package org.example.objects;

import java.util.HashMap;

public class ContextObject implements UObject {
    private static ContextObject instance;
    private final HashMap<String, Object> context;

    public ContextObject(){
        this.context = new HashMap<>();
    }

    public static synchronized ContextObject getInstance() {
        if (instance == null) {instance = new ContextObject();}
        return instance;
    }

    @Override
    public Object getProperty(String key) {
        return context.get(key);
    }

    @Override
    public void setProperty(String key, Object newValue) {
        context.put(key, newValue);
    }
}
