package com.szyl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.szyl.dao.UserDao;
import com.szyl.entity.User;

public class UserDaoTest {

    private UserDao userDao = new UserDao();
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUserDao() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
        User user = new User("־��","111","��������Ա");
        int result = userDao.add(user);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        int result = userDao.delete("־��");
        System.out.println(result);
    }

    @Test
    public void testUpdate() {
        User user = new User("־��","2222","��ͨ�û�");
        int result = userDao.update(user);
        System.out.println(result);
    }

    @Test
    public void testGet() {
        User user = userDao.get("־��");
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        List<User> user = userDao.getAll();
        System.out.println(user);
    }

    @Test
    public void testGetAllString() {
        fail("Not yet implemented");
    }

}
