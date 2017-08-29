package com.cn.nlg.lp.ssm.base.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 */
@Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
