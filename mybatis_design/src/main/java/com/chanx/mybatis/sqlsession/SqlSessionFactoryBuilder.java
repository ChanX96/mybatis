package com.chanx.mybatis.sqlsession;

import com.chanx.mybatis.cfg.Configuration;
import com.chanx.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.chanx.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 创建SqlSessionFactory建造者
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据字节参数输入流构建SqlSessionFactory
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
