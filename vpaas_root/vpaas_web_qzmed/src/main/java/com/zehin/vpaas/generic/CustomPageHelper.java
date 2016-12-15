package com.zehin.vpaas.generic;

import java.util.*;

import org.apache.ibatis.executor.*;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.*;

import com.github.pagehelper.*;

@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class CustomPageHelper implements Interceptor {

    private PageHelper mysqlPageHelper = new PageHelper();

    private PageHelper oraclePageHelper = new PageHelper();

    private PageHelper postgresqlPageHelper = new PageHelper();
    private PageHelper sqlserverPageHelper = new PageHelper();

    private Map<Object, PageHelper> targetPageHelper = new HashMap<>();

    private PageHelper defaultPageHelper;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return determinePageHelper().intercept(invocation);
    }

    @Override
    public Object plugin(Object target) {
        /*return determinePageHelper().plugin(target);*/
        //determinePageHelper();
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        targetPageHelper.put(Dialect.mysql.name(), mysqlPageHelper);
        targetPageHelper.put(Dialect.oracle.name(), oraclePageHelper);
        targetPageHelper.put(Dialect.postgresql.name(), postgresqlPageHelper);
        targetPageHelper.put(Dialect.sqlserver.name(), sqlserverPageHelper);
        //数据库方言
        String dialect = properties.getProperty("dialect");
        if(Dialect.oracle.equals(Dialect.valueOf(dialect.toLowerCase()))) {
            defaultPageHelper = oraclePageHelper;
        } else if(Dialect.postgresql.equals(Dialect.valueOf(dialect.toLowerCase()))) {
            defaultPageHelper = postgresqlPageHelper;
        } else if(Dialect.sqlserver.equals(Dialect.valueOf(dialect.toLowerCase()))) {
        	defaultPageHelper = sqlserverPageHelper;
        } else {
            defaultPageHelper = mysqlPageHelper;
        }

        properties.put("dialect", Dialect.mysql.name());
        mysqlPageHelper.setProperties(properties);

        properties.put("dialect", Dialect.oracle.name());
        oraclePageHelper.setProperties(properties);

        properties.put("dialect", Dialect.postgresql.name());
        postgresqlPageHelper.setProperties(properties);

        properties.put("dialect", Dialect.sqlserver.name());
        sqlserverPageHelper.setProperties(properties);

        
        properties.put("dialect", dialect);
    }

    private PageHelper determinePageHelper() {
        String pageType = CustomerContextHolder.getCustomerType();
        PageHelper pageHelper =  null;
        if (pageType.equals(CustomerContextHolder.DATA_SOURCE_MSSQL)) {
        	pageHelper = targetPageHelper.get(Dialect.sqlserver.name());
        }
        if(pageType.equals(CustomerContextHolder.DATA_SOURCE_ORACLE)) {
        	pageHelper = targetPageHelper.get(Dialect.oracle.name());
        }
        if (pageHelper != null) {
            return pageHelper;
        } else {
            return defaultPageHelper;
        }
    }
}