package com.chanx.test;

import com.chanx.dao.UserDao;
import com.chanx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试mybatis的crud操作
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSession sqlSession;
    private SqlSessionFactory factory;
    private UserDao userDao;

    /**
     * 初始化方法
     */
    @Before
    public void init() throws IOException {

        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    /**
     * 释放资源
     */
    @After
    public void destroy() throws IOException {

        in.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache() {

        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(45);
        System.out.println(user1);
        sqlSession1.close(); // 一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = userDao2.findById(45);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user1 == user2);
    }

    @Test
    public void testClearCache() {

        // 1.根据id查询用户
        User user1 = userDao.findById(45);
        System.out.println(user1);
        // 2.更新用户信息
        user1.setUsername("李十八");
        user1.setAddress("武汉市");
        userDao.updateUser(user1);

        // 再次查询该用户
        User user2 = userDao.findById(45);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
