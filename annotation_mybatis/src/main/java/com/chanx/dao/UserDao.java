package com.chanx.dao;

import com.chanx.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中针对CRUD一共有四个注解
 * @Select @Insert @Delete @Update
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("INSERT INTO user(username,address,sex,birthday)VALUES(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("UPDATE user SET username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} WHERE id=#{id};")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteUser(Integer id);

    /**
     * 查询一个
     * @param id
     */
    @Select("SELECT * FROM user WHERE id=#{uid}")
    User findById(Integer id);
}
