package com.cn.nlg.lp.ssm.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Administrator on 2017/6/26.
 *管理springmvc的相关配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cn.nlg.lp")
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean //内部资源视图解析器 配置 这是springmvc的核心渲染机制
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //配置前后缀
        viewResolver.setPrefix("/views");
        viewResolver.setSuffix(".jsp");
        //配置渲染方式.这里是Jstl 可以采用freemark或者其他静态模板的方式
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }


    //静态资源管理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }
/*
// 配置拦截器
    @Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }
  //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }
*/
    //空白转向注册
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }

    //设置不忽略小数点后面的参数
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(false);

    }


}
