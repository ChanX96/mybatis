package com.chanx.dao;

import com.chanx.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {

    /**
     * 查询所有结果，同时获取用户下所有账户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 更新对象
     * @param user
     */
    void updateUser(User user);
}
