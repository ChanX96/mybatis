package com.chanx.dao;

import com.chanx.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();
}
