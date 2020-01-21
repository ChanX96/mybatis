package com.chanx.dao.impl;

import com.chanx.dao.UserDao;
import com.chanx.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.chanx.dao.UserDao.findAll");// 参数就是能获取配置信息的key
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用方法，实现保存
        session.insert("com.chanx.dao.UserDao.saveUser", user);
        // 3.提交事务
        session.commit();
        // 4.释放资源
        session.close();
    }

    @Override
    public void updateUser(User user) {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用方法，实现保存
        session.update("com.chanx.dao.UserDao.updateUser", user);
        // 3.提交事务
        session.commit();
        // 4.释放资源
        session.close();
    }

    @Override
    public void deleteUser(Integer id) {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用方法，实现保存
        session.delete("com.chanx.dao.UserDao.deleteUser", id);
        // 3.提交事务
        session.commit();
        // 4.释放资源
        session.close();
    }

    @Override
    public User findById(Integer id) {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用SqlSession中的方法，实现查询列表
        User user = session.selectOne("com.chanx.dao.UserDao.findById", id);// 参数就是能获取配置信息的key
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String username) {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.chanx.dao.UserDao.findByName", username);// 参数就是能获取配置信息的key
        session.close();
        return users;
    }

    @Override
    public int findTotal() {

        // 1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        // 2.调用SqlSession中的方法，实现查询列表
        Integer count = session.selectOne("com.chanx.dao.UserDao.findTotal");// 参数就是能获取配置信息的key
        session.close();
        return count;
    }
}
