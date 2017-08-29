package com.cn.nlg.lp.ssm.mybatis.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（数据源切换）
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 获取数据源
     */
    public static String getDataSource() {
        String dataSource = contextHolder.get();
        // 如果没有指定数据源，使用默认数据源
        if (null == dataSource) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return contextHolder.get();
    }

    /**
     * 设置数据源
     */
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = getDataSource();
        log.info("当前操作使用的数据源：{}", dataSource);
        return dataSource;
    }

}
