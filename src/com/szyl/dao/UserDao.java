package com.szyl.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.szyl.entity.User;
import com.szyl.mapping.UserMapperI;

@Repository
public class UserDao {

    // ����sql�Ự
    SqlSession sqlSession;
    // ��ȡmapper�ļ���Ϣ
    UserMapperI mapper;

    public UserDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * �û�����Ӳ���
     * 
     * @param user
     *            �û�ʵ����
     * @return ���ز������(int����)
     */
    public int add(User user) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
        mapper = sqlSession.getMapper(UserMapperI.class);
        int retResult = 0;
        try {
            // ִ����Ӳ���
            retResult = mapper.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * �û���ɾ������
     * 
     * @param username
     *            �û���
     * @return ���ز������(int����)
     */
    public int delete(String username) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
        mapper = sqlSession.getMapper(UserMapperI.class);
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
     * �û�����²���
     * 
     * @param user
     *            �û���ʵ����
     * @return ���ز������(int����)
     */
    public int update(User user) {
        sqlSession = MyBatisUtil.getSqlSession(true);
        // �õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
        mapper = sqlSession.getMapper(UserMapperI.class);
        int retResult = 0;
        try {
            // ִ����Ӳ���
            retResult = mapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return retResult;
    }

    /**
     * �û����ѯ����(Ĭ�ϲ�ѯ����)
     * 
     * @param username
     *            �û���
     * @return ���� �û���ʵ����
     */
    public User get(String username) {
        sqlSession = MyBatisUtil.getSqlSession();
        // �õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
        mapper = sqlSession.getMapper(UserMapperI.class);
        User user = new User();
        try {
            // ִ�в�ѯ����������ѯ����Զ���װ��User����
            user = mapper.get(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return user;
    }

    /**
     * �û����ѯ���в���(Ĭ�ϲ�ѯ����)
     * 
     * @return ���� �û���ʵ����list����
     */
    public List<User> getAll() {
        List<User> listUser = getAll("");
        return listUser;
    }

    /**
     * �û����ѯ���в���(���û�����ѯ)
     * 
     * @return ���� �û���ʵ����list����
     */
    public List<User> getAll(String username) {
        sqlSession = MyBatisUtil.getSqlSession();
        // �õ�UserMapperI�ӿڵ�ʵ�������UserMapperI�ӿڵ�ʵ���������sqlSession.getMapper(UserMapperI.class)��̬��������
        mapper = sqlSession.getMapper(UserMapperI.class);
        List<User> listUser = new ArrayList<User>();
        try {
            // ִ�в�ѯ����������ѯ����Զ���װ��List<User>����
            String str = "%";
            for (int i = 0; i < username.length(); i++) {
                str += username.charAt(i) + "%";
            }
            listUser = mapper.getAll(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession
            sqlSession.close();
        }
        return listUser;
    }

}
