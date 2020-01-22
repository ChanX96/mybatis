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
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的条件查询
     * @param user 有可能有用户名，也有可能有性别、地址或者生日，也有可能都没有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中提供的id集合查询用户
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
