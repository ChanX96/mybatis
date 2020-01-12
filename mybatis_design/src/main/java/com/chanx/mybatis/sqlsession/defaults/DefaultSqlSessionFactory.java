package com.chanx.mybatis.sqlsession.defaults;

import com.chanx.mybatis.cfg.Configuration;
import com.chanx.mybatis.sqlsession.SqlSession;
import com.chanx.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建新的连接
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
