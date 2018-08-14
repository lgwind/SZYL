package com.szyl.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.szyl.entity.Resume;
import com.szyl.mapping.ResumeMapperI;

@Repository
public class ResumeDao {

    // 创建sql会话
    SqlSession sqlSession;
    // 获取mapper文件信息
    ResumeMapperI mapper;

    public ResumeDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 简历表添加操作
     * 
     * @param Resume
     *            用户实体类
     * @return 返回操作结果(int数据)
     */
    public int add(Resume resume) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // 执行添加操作
            retResult = mapper.add(resume);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * 简历表删除操作
     * 
     * @param username
     *            用户名
     * @return 返回操作结果(int数据)
     */
    public int delete(String username) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // 执行添加操作
            retResult = mapper.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * 简历表更新操作
     * 
     * @param Resume
     *            简历表实体类
     * @return 返回操作结果(int数据)
     */
    public int update(Resume resume) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // 执行添加操作
            retResult = mapper.update(resume);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * 简历表查询操作(默认查询所有)
     * 
     * @param username
     *            用户名
     * @return 返回 简历表实体类
     */
    public Resume get(String resume_id) {
        sqlSession = MyBatisUtil.getSqlSession();
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        Resume Resume = new Resume();
        try {
            // 执行查询操作，将查询结果自动封装成Resume返回
            Resume = mapper.get(resume_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return Resume;
    }

    /**
     * 简历表查询多行操作(默认查询所有)
     * 
     * @return 返回 简历表实体类list集合
     */
    public List<Resume> getAll() {
        sqlSession = MyBatisUtil.getSqlSession();
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        List<Resume> listResume = new ArrayList<Resume>();
        try {
            // 执行查询操作，将查询结果自动封装成List<Resume>返回
            listResume = mapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return listResume;
    }

    /**
     * 简历表查询多行操作(按用户名查询)
     * 
     * @return 返回 简历表实体类list集合
     */
    public List<Resume> getAll(String username) {
        sqlSession = MyBatisUtil.getSqlSession();
        // 得到ResumeMapperI接口的实现类对象，ResumeMapperI接口的实现类对象由sqlSession.getMapper(ResumeMapperI.class)动态构建出来
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        List<Resume> listResume = new ArrayList<Resume>();
        try {
            // 执行查询操作，将查询结果自动封装成List<Resume>返回
            String str = "%";
            for (int i = 0; i < username.length(); i++) {
                str += username.charAt(i) + "%";
            }
            listResume = mapper.getAllByUsername(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用SqlSession执行完SQL之后需要关闭SqlSession
            sqlSession.close();
        }
        return listResume;
    }

}
