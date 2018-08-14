package com.szyl.mapping;

import java.util.List;

import com.szyl.entity.Resume;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ����������sqlӳ��ӿڣ�ʹ��ע��ָ������Ҫִ�е�SQL���
 * 
 * @author lgwind
 * 
 */
public interface ResumeMapperI {
	
	/**
	 * sql���
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

	// ʹ��@Insertע��ָ��add����Ҫִ�е�SQL
	@Insert(insert)
    public int add(Resume resume);

	// ʹ��@Deleteע��ָ��deleteById����Ҫִ�е�SQL
	@Delete(delete)
    public int delete(String resume_id);

	// ʹ��@Updateע��ָ��update����Ҫִ�е�SQL
	@Update(update)
    public int update(Resume resume);

	// ʹ��@Selectע��ָ��getById����Ҫִ�е�SQL
	@Select(select)
    public Resume get(String resume_id);

	// ʹ��@Selectע��ָ��getAll����Ҫִ�е�SQL
	@Select(selectAll)
    public List<Resume> getAll();
	
	// ʹ��@Selectע��ָ��getAll����Ҫִ�е�SQL
    @Select(selectAllByUsername)
    public List<Resume> getAllByUsername(String username);
}