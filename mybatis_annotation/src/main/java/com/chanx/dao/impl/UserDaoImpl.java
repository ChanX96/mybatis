package com.chanx.dao.impl;

import com.chanx.dao.UserDao;
import com.chanx.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * UserDao实现类
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }
    
    @Override
    public List<User> findAll() {
        
        // 使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        // 使用session查询所有
        List<User> users = session.selectList("com.chanx.dao.UserDao.findAll");
        // 返回查询结果
        session.close();
        return users;
    }
}
