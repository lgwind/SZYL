package com.szyl.mapping;

import java.util.List;

import com.szyl.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 定义用户表的sql映射接口，使用注解指明方法要执行的SQL语句
 * 
 * @author lgwind
 * 
 */
public interface UserMapperI {
	
	/**
	 * sql语句
	 */
	String insert = "insert into user(username,password,role) "
			+ "values(#{username},#{password},#{role})";
	String delete = "delete from user "
			+ "where username=#{username}";
	String update = "update user set password=#{password},role=#{role} "
			+ "where username=#{username}";
	String select = "select * from user "
			+ "where username=#{username}";
	String selectAll = "select * from user where username like #{username}";

	// 使用@Insert注解指明add方法要执行的SQL
	@Insert(insert)
    public int add(User user);

	// 使用@Delete注解指明deleteById方法要执行的SQL
	@Delete(delete)
    public int delete(String username);

	// 使用@Update注解指明update方法要执行的SQL
	@Update(update)
    public int update(User user);

	// 使用@Select注解指明getById方法要执行的SQL
	@Select(select)
    public User get(String username);

	// 使用@Select注解指明getAll方法要执行的SQL
	@Select(selectAll)
    public List<User> getAll(String username);
}