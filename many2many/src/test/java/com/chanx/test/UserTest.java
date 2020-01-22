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
    private UserDao userDao;

    /**
     * 初始化方法
     */
    @Before
    public void init() throws IOException {

        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
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
     * 测试查询所有
     */
    @Test
    public void testFindAll() {

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("----------每个user的信息-----------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }
}
