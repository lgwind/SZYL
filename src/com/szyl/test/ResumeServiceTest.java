package com.szyl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.szyl.entity.Resume;
import com.szyl.service.ResumeService;

public class ResumeServiceTest {
    
    ResumeService resumeService = new ResumeService();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdd() {
        Resume resume = new Resume();
        int result = resumeService.add(resume);
        System.out.println(result);
    }

    @Test
    public void testUpdate() {
        Resume resume = new Resume();
        resume.setResume_id("test");
        resume.setUsername("test");
        resume.setName("test");
        resume.setPhone("test");
        resume.setEmail("test");
        resume.setSchool("test");
        resume.setMajor("test");
        resume.setPosition("test");
        resume.setAge(27);
        resume.setState("test");
        resume.setWork_age(5);
        resume.setSkill("test");
        resume.setEml("test");
        resume.setUpdate_time("2018-07-20");
        int result = resumeService.update(resume);
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        int result = resumeService.delete("01b9424f-c6bc-4d1a-a144-8dd898fd25fa");
        System.out.println(result);
    }

    @Test
    public void testGet() {
        Resume result = resumeService.get("test");
        System.out.println(result);
    }

    @Test
    public void testGetAll() {
        List<Resume> result = resumeService.getAll();
        System.out.println(result);
    }

    @Test
    public void testGetAllString() {
        fail("Not yet implemented");
    }

}
