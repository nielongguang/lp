package com.cn.nlg.lp.jdk8;

import com.cn.nlg.lp.jdk8.kata.ForEach;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;

/**
 * ForEachDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>八月 31, 2017</pre>
 */

@Slf4j
public class ForEachTest {

    private ForEachDemo forEach = new ForEachDemo();
    private ForEach forEach1 = new ForEach();

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
   Method method = ForEachDemo.getClass().getMethod("foreachBase");
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
   Method method = ForEachDemo.getClass().getMethod("foreachNew");
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    @Test
    public void testExecutor() {
        this.forEach1.execute();
        log.debug("运行完毕");
    }
}
