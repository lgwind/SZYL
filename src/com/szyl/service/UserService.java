package com.szyl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.szyl.dao.UserDao;
import com.szyl.entity.User;

@Service("UserService")
public class UserService{

	private UserDao userDao = new UserDao();
	
	public int add(User user) {
        return userDao.add(user);
	}
	
	public int update(User user) {
	    return userDao.update(user);
	}
	
	public int delete(String username) {
	    return userDao.delete(username);
	}
	
	public User get(String username) {
	    return userDao.get(username);	    
	}
	
	public List<User> getAll(){
	    return userDao.getAll();
	}	
	
}
