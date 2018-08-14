package com.szyl.mapping;

import java.util.List;

import com.szyl.entity.Resume;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 定义简历表的sql映射接口，使用注解指明方法要执行的SQL语句
 * 
 * @author lgwind
 * 
 */
public interface ResumeMapperI {
	
	/**
	 * sql语句
	 */
	String insert = "insert into resume(resume_id,username,name,phone,email,"
	        + "school,major,position,age,state,"
	        + "work_age,skill,eml,update_time) "
			+ "values(#{resume_id},#{username},#{name},#{phone},#{email}"
			+ ",#{school},#{major},#{position},#{age},#{state}"
			+ ",#{work_age},#{skill},#{eml},#{update_time})";
	String update = "update resume set username=#{username},name=#{name},phone=#{phone},email=#{email}"
	        + ",school=#{school},major=#{major},position=#{position},age=#{age},state=#{state}"
	        + ",work_age=#{work_age},skill=#{skill},eml=#{eml},update_time=#{update_time} "
			+ "where resume_id=#{resume_id}";
    String delete = "delete from resume "
            + "where resume_id=#{resume_id}";
	String select = "select * from resume "
			+ "where resume_id=#{resume_id}";
	String selectAll = "select * from resume";
    String selectAllByUsername = "select * from resume where username like #{username}";

	// 使用@Insert注解指明add方法要执行的SQL
	@Insert(insert)
    public int add(Resume resume);

	// 使用@Delete注解指明deleteById方法要执行的SQL
	@Delete(delete)
    public int delete(String resume_id);

	// 使用@Update注解指明update方法要执行的SQL
	@Update(update)
    public int update(Resume resume);

	// 使用@Select注解指明getById方法要执行的SQL
	@Select(select)
    public Resume get(String resume_id);

	// 使用@Select注解指明getAll方法要执行的SQL
	@Select(selectAll)
    public List<Resume> getAll();
	
	// 使用@Select注解指明getAll方法要执行的SQL
    @Select(selectAllByUsername)
    public List<Resume> getAllByUsername(String username);
}