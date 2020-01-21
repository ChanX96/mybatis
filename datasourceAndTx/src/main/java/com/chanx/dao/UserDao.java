package com.chanx.dao;

import com.chanx.domain.QueryVo;
import com.chanx.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {

    /**
     * 查询所有结果
     * @return
     */
    List<User> findAll();

    /**
     * 保存方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 聚合操作，查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
