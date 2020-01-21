package com.chanx.test;

import com.chanx.dao.UserDao;
import com.chanx.dao.impl.UserDaoImpl;
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
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
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
        // 3.使用工厂对象创建Dao对象
        userDao = new UserDaoImpl(factory);
    }

    /**
     * 释放资源
     */
    @After
    public void destroy() throws IOException {

        in.close();
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() throws IOException {

        // 5.执行查询所有
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("李老八");
        user.setAddress("成都");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存前: " + user);

        // 5.保存对象
        userDao.saveUser(user);
        System.out.println("保存后: " + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() throws IOException {
        User user = new User();
        user.setId(44);
        user.setUsername("张三");
        user.setAddress("河南省");
        user.setSex("女");
        user.setBirthday(new Date());

        // 5.更新对象
        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() throws IOException {

        // 5.删除对象
        userDao.deleteUser(44);
    }

    /**
     * 测试查询一个方法
     */
    @Test
    public void testFindOne() throws IOException {

        // 5.查询一个
        User user = userDao.findById(41);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() throws IOException {

        // 5.查询一个
        List<User> users = userDao.findByName("%老%");
//        List<User> users = userDao.findByName("老");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() throws IOException {

        // 5.查询总记录条数
        int count = userDao.findTotal();
        System.out.println(count);
    }
}
