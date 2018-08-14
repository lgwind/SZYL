package com.szyl.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.szyl.entity.Resume;
import com.szyl.mapping.ResumeMapperI;

@Repository
public class ResumeDao {

    // ����sql�Ự
    SqlSession sqlSession;
    // ��ȡmapper�ļ���Ϣ
    ResumeMapperI mapper;

    public ResumeDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * ��������Ӳ���
     * 
     * @param Resume
     *            �û�ʵ����
     * @return ���ز������(int����)
     */
    public int add(Resume resume) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // ִ����Ӳ���
            retResult = mapper.add(resume);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * ������ɾ������
     * 
     * @param username
     *            �û���
     * @return ���ز������(int����)
     */
    public int delete(String username) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // ִ����Ӳ���
            retResult = mapper.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * ��������²���
     * 
     * @param Resume
     *            ������ʵ����
     * @return ���ز������(int����)
     */
    public int update(Resume resume) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        int retResult = 0;
        try {
            // ִ����Ӳ���
            retResult = mapper.update(resume);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * �������ѯ����(Ĭ�ϲ�ѯ����)
     * 
     * @param username
     *            �û���
     * @return ���� ������ʵ����
     */
    public Resume get(String resume_id) {
        sqlSession = MyBatisUtil.getSqlSession();
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        Resume Resume = new Resume();
        try {
            // ִ�в�ѯ����������ѯ����Զ���װ��Resume����
            Resume = mapper.get(resume_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return Resume;
    }

    /**
     * �������ѯ���в���(Ĭ�ϲ�ѯ����)
     * 
     * @return ���� ������ʵ����list����
     */
    public List<Resume> getAll() {
        sqlSession = MyBatisUtil.getSqlSession();
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        List<Resume> listResume = new ArrayList<Resume>();
        try {
            // ִ�в�ѯ����������ѯ����Զ���װ��List<Resume>����
            listResume = mapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return listResume;
    }

    /**
     * �������ѯ���в���(���û�����ѯ)
     * 
     * @return ���� ������ʵ����list����
     */
    public List<Resume> getAll(String username) {
        sqlSession = MyBatisUtil.getSqlSession();
        // �õ�ResumeMapperI�ӿڵ�ʵ�������ResumeMapperI�ӿڵ�ʵ���������sqlSession.getMapper(ResumeMapperI.class)��̬��������
        mapper = sqlSession.getMapper(ResumeMapperI.class);
        List<Resume> listResume = new ArrayList<Resume>();
        try {
            // ִ�в�ѯ����������ѯ����Զ���װ��List<Resume>����
            String str = "%";
            for (int i = 0; i < username.length(); i++) {
                str += username.charAt(i) + "%";
            }
            listResume = mapper.getAllByUsername(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return listResume;
    }

}
