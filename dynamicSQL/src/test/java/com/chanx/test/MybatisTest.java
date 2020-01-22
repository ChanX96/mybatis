package com.chanx.test;

import com.chanx.dao.UserDao;
import com.chanx.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {

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
     * 测试查询一个方法
     */
    @Test
    public void testFindOne() throws IOException {

        // 5.查询一个
        User user = userDao.findById(32);
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
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() throws IOException {

        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%老%");
        vo.setUser(user);

        // 5.查询一个
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindByCondition() {

        User user = new User();
//        user.setUserName("李老八");
        user.setUserSex("男");

        // 5.执行查询所有
        List<User> users = userDao.findUserByCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 测试子查询foreach标签的使用
     */
    @Test
    public void testFindUserInIds() {

        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(45);
        list.add(47);
        vo.setIds(list);

        // 5.执行查询所有
        List<User> users = userDao.findUserInIds(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
