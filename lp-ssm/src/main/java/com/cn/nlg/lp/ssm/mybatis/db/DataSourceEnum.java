package com.cn.nlg.lp.ssm.mybatis.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 多数据源枚举
 */

@AllArgsConstructor
public enum DataSourceEnum {

    // 主库
    @NonNull
    MASTER("masterDataSource", true),
    // 从库
    SLAVE("slaveDataSource", false),;

    // 数据源名称
    @Getter
    @Setter
    private String name;
    // 是否是默认数据源
    @Getter
    @Setter
    private boolean master;


    public String getDefault() {
        String defaultDataSource = "";
        for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
            if (!"".equals(defaultDataSource)) {
                break;
            }
            if (dataSourceEnum.master) {
                defaultDataSource = dataSourceEnum.getName();
            }
        }
        return defaultDataSource;
    }

}
