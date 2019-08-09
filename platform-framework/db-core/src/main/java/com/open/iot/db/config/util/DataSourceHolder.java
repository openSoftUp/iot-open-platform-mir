package com.open.iot.db.config.util;


/**
 * 
* @ClassName: DataSourceHolder  
* @Description: 数据源切换
* @author auth  
* @date 2019年8月9日  
*
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceKey> dataSourceKey = new ThreadLocal<>();

    public static DataSourceKey getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void setDataSourceKey(DataSourceKey type) {
        dataSourceKey.set(type);
    }

    public static void clearDataSourceKey() {
        dataSourceKey.remove();
    }


}