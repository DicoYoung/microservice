package com.example.provider8001.repository;

import com.example.provider8001.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    //添加
    @Insert("insert into tb_user(login_name ,username ,password) "
            + "values (#{loginName},#{username},#{password})")
    public int insertUser(User user);

    //主键自维护添加
    @Insert("insert into tb_user(login_name ,username ,password ,db_source) "
            + "values (#{loginName},#{username},#{password},#{dbSource})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void insertGetKey(User user);

    //查询，通过姓名
    @Select("select * from tb_user where username = #{username}")
    // 引用id="userResult"的@Results
    @ResultMap("userResult")
    public User selectByUsername(@Param("username") String username);

    //查询整个表
    @Select("select * from tb_user")
    // @Results用于映射对象属性和数据库列，常用于对象属性和数据库列不同名情况
    @Results(id="userResult",value={
            @Result(id=true,column="id",property="id"),
            @Result(column="login_name",property="loginName"),
            @Result(column="password",property="password"),
            @Result(column="username",property="username"),
            @Result(column="db_source",property="dbSource")
    })
    public List<User> findAll();

    //删除
    @Delete("delete from tb_user where id=#{id}")
    public int delete(final Integer id);

    //查询，通过ID
    @Select("select * from tb_user where id=#{id}")
    // 引用id="userResult"的@Results
    @ResultMap("userResult")
    public User findUserById(int id);

    //更新,根据id
    @Update("update tb_user set username=#{username}, login_name=#{loginName}, password=#{password} where id=#{id}")
    public int update(User user);
}
