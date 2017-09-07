package com.cn.nlg.lp.jdk8.Factory;

@FunctionalInterface
public interface Factory<T> {
    T newInstans(Object o, String name);
}
