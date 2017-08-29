package com.cn.nlg.lp.service;

import com.cn.nlg.lp.ssm.base.annotation.BaseService;
import com.cn.nlg.lp.ssm.mybatis.dao.DAO;

import javax.annotation.Resource;
import javax.servlet.jsp.tagext.PageData;

@BaseService
public class basicServerImpl {

    @Resource
    DAO dao;


    public PageData queryReal(String userName) throws Exception {
        return (PageData) dao.findForObject("test.queryReal", userName);
    }


}
