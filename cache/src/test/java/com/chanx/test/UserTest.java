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
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class UserTest {

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
        // 3.获取SqlSession对象
        sqlSession = factory.openSession();
        // 4.获取代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    /**
     * 释放资源
     */
    @After
    public void destroy() throws IOException {

        // 提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache() {

        User user1 = userDao.findById(45);
        System.out.println(user1);
//        sqlSession.close();
        // 再次获取sqlSession对象
//        sqlSession = factory.openSession();
//        userDao = sqlSession.getMapper(UserDao.class);

        sqlSession.clearCache(); // 此方法也可以清空缓存
        User user2 = userDao.findById(45);
        System.out.println(user2);

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
