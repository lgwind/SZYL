package com.szyl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.szyl.dao.ResumeDao;
import com.szyl.entity.Resume;

@Service("ResumeService")
public class ResumeService{

	private ResumeDao ResumeDao = new ResumeDao();
	
	public int add(Resume resume) {
        return ResumeDao.add(resume);
	}
	
	public int update(Resume resume) {
	    return ResumeDao.update(resume);
	}
	
	public int delete(String resume_id) {
	    return ResumeDao.delete(resume_id);
	}
	
	public Resume get(String resume_id) {
	    return ResumeDao.get(resume_id);	    
	}
	
	public List<Resume> getAll(){
	    return ResumeDao.getAll();
	}	
	
	public List<Resume> getAll(String username){
        return ResumeDao.getAll(username);
    }
	
}
