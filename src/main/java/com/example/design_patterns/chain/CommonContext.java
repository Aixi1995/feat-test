package com.example.design_patterns.chain;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2021/3/20 13:42
 * @description
 */
public class CommonContext implements Context {

    private String name;

    private Integer age;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
