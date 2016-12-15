package com.zehin.vpaas.generic;

import com.alibaba.druid.util.StringUtils;

public class CustomerContextHolder {
	public static final String DATA_SOURCE_ORACLE = "dataSourceOne";
    public static final String DATA_SOURCE_MSSQL = "dataSourceTwo";
    
    //用ThreadLocal来设置当前线程使用哪个dataSource
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        String dataSource = contextHolder.get();
        if (StringUtils.isEmpty(dataSource)) {
            return DATA_SOURCE_MSSQL;
        }else {
            return dataSource;
        }
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }

}
