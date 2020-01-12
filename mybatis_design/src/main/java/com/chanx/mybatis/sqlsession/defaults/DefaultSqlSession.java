package com.chanx.mybatis.sqlsession.defaults;

import com.chanx.mybatis.cfg.Configuration;
import com.chanx.mybatis.sqlsession.SqlSession;
import com.chanx.mybatis.sqlsession.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * SqlSessionc的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(), new Class[] {daoInterfaceClass}, new MapperProxy());
        return null;
    }

    /**
     * 用于关闭连接，释放资源
     */
    @Override
    public void close() {

    }
}
