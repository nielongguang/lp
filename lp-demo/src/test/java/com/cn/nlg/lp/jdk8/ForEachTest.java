package com.cn.nlg.lp.jdk8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ForEach Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>°ËÔÂ 31, 2017</pre>
 */
public class ForEachTest {

    private ForEach forEach = new ForEach();

    @Before
    public void before() throws Exception {
        forEach.put();
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: foreachBase()
     */
    @Test
    public void testForeachBase() throws Exception {
        forEach.foreachBase();
/*
try {
   Method method = ForEach.getClass().getMethod("foreachBase");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

    /**
     * Method: foreachNew()
     */
    @Test
    public void testForeachNew() throws Exception {
        forEach.foreachBase();
/* 
try { 
   Method method = ForEach.getClass().getMethod("foreachNew"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
