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
     * 测试保存操作
     */
    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUserName("孙笑川");
        user.setUserAddress("江津");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
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
        user.setUserId(41);
        user.setUserName("张三");
        user.setUserAddress("河南省");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

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
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() throws IOException {

        // 5.查询总记录条数
        int count = userDao.findTotal();
        System.out.println(count);
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
}
