package com.cn.nlg.lp.ssm.mybatis.dao;


public interface DAO {

    /**
     * 保存对象
     */
    Object save(String str, Object obj) throws Exception;

    /**
     * 修改对象
     */
    Object update(String str, Object obj) throws Exception;

    /**
     * 删除对象
     */
    Object delete(String str, Object obj) throws Exception;

    /**
     * 查找对象
     */
    Object findForObject(String str, Object obj) throws Exception;

    /**
     * 查找对象
     */
    Object findForList(String str, Object obj) throws Exception;

    /**
     * 查找对象封装成Map
     *
     * @param sql 要查找的对象
     */
    Object findForMap(String sql, Object obj, String key, String value) throws Exception;
}
