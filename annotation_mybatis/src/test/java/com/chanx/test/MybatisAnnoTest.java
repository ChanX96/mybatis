package com.chanx.test;

import com.chanx.dao.UserDao;
import com.chanx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {

    /**
     * 测试基于注解的Mybatis使用
     * @param args
     */
    public static void main(String[] args) throws IOException {

        // 1.获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.根据字节输入流获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.根据SqlSessionFactory生产一个SqlSession
        SqlSession session = factory.openSession();
        // 4.使用SqlSession获取Dao代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        // 5.执行Dao的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        session.close();
        in.close();
    }
}
