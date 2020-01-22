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
import java.util.Date;

public class AnnotationCrudTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws IOException {

        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {

        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave() {

        User user = new User();
        user.setUsername("哈哈哈");
        user.setAddress("辽宁");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    @Test
    public void testUpdate() {

        User user = new User();
        user.setId(45);
        user.setUsername("李老七");
        user.setAddress("合肥");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    @Test
    public void testDelete() {

        userDao.deleteUser(48);
    }

    @Test
    public void testFidnOne() {

        User user = userDao.findById(45);
        System.out.println(user);
    }
}
