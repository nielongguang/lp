package com.cn.nlg.lp.ssm.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by Administrator on 2017/6/26.
 */


public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfiguration.class);
        //在此处可以增加监听器 拦截器 效果同在web.xml里面配置
        //如果此处配置了,则默认在WEB-INF下应该有applicationcontext.xml文件 或者可以在web.xml中配置配置文件属性
        //servletContext.addListener(ContextLoaderListener.class);
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }


}
