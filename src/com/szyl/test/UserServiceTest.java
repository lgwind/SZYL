package com.szyl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.szyl.entity.User;
import com.szyl.service.UserService;

public class UserServiceTest {
    
    private UserService userSevice = new UserService();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testuserSevice() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
        User user = new User("־��","111","��������Ա");
        int result = userSevice.add(user);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        int result = userSevice.delete("־��");
        System.out.println(result);
    }

    @Test
    public void testUpdate() {
        User user = new User("־��","2222","��ͨ�û�");
        int result = userSevice.update(user);
        System.out.println(result);
    }

    @Test
    public void testGet() {
        User user = userSevice.get("־��");
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        List<User> user = userSevice.getAll();
        System.out.println(user);
    }

}
