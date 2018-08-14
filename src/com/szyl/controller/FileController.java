package com.szyl.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.szyl.entity.Resume;
import com.szyl.entity.User;
import com.szyl.service.ResumeService;
import com.szyl.util.Default;
import com.szyl.util.CopyFile;
import com.szyl.util.Eml;
import com.szyl.util.FileIO;
import com.szyl.util.ResumeValue;

/**
 * ������ӿ���
 * 
 * @author chennaile
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private ResumeService resumeService = new ResumeService();
    
    @RequestMapping("/import2")
    @ResponseBody
    public boolean importing2(User user) {
        System.out.println(user.getUsername());
        return false;
    }

    /**
     * �ϴ��ļ�
     * 
     * @param username
     * @return
     */
    @RequestMapping(value = "/import")
    @ResponseBody
    public boolean importing(HttpServletRequest request, @RequestParam(value="username",required=false) String username) {
        System.out.println("/* ���õ����ļ����ݽӿ� */");
        if (username == null) {
            System.out.println("�û���Ϊ��,ʹ��Ĭ���û�����" + Default.username);
            username = Default.username;
        }else{
            System.out.println("�û�����" + username);            
        }
        boolean bool = true;
        try{
            // ����һ����ֽ������
            CommonsMultipartResolver cmr = new CommonsMultipartResolver(request
                    .getSession().getServletContext());
            // ���ñ���
            cmr.setDefaultEncoding("utf-8");
            // �ж��Ƿ����ļ��ϴ�
            if (cmr.isMultipart(request)) {
                // ��requestת���ɶ�ֽ�����
                MultipartHttpServletRequest mhs = cmr.resolveMultipart(request);
                // ����input�д��ڵ�name����ȡ�Ƿ�����ϴ��ļ�
                List<MultipartFile> mf = mhs.getFiles("file");// Ҳ������getFile("file")�ķ�ʽ���������ļ�,���ص���MultipartFile
                List<File> files = new ArrayList<File>();
                for (int i = 0; i < mf.size(); i++) {
                    //�����ļ�������
                    File file = new File(Default.emlPath + "buffer\\"+UUID.randomUUID().toString()+".eml");
                    // �ϴ��ļ�
                    mf.get(i).transferTo(file);
                    //��ȡ�ļ���׺��
                    String suffix = file.getName().substring(
                            file.getName().lastIndexOf(".") + 1);
                    if (suffix.equals("eml")) {
                        files.add(file);
                    }else{
                        System.out.println("������ļ���ʽ���ԣ�����");
                    }
                }
                DealWithFiles(files, username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("������");
            bool = false;
        }
        return bool;
    }
    
    /**
     * ����eml�ļ��б������ļ����ݣ�����Ϊ�����б�List<Resume>�����浽���ݿ���
     * @param files
     * @param username
     * @throws Exception
     */
    private void DealWithFiles(List<File> files, String username) throws Exception {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            System.out.println("��ʱ�ļ���ַ��" + file.getAbsolutePath());
            // ��ȡresume����
            String uuid = UUID.randomUUID().toString();
            // �ƶ��ļ�
            new CopyFile().moveFile(file.getAbsolutePath(), Default.emlPath
                    + uuid + ".eml");
            System.out.println("�����ļ���ַ��"+ Default.emlPath
                    + uuid + ".eml");
            // ��ȡ�ļ�����
            String content = Eml.getContent(Default.emlPath + uuid + ".eml", uuid);
            // System.out.println(content);
            // �����ļ�����
            Resume resume = ResumeValue.get(content);
            resume.setResume_id(uuid);
            resume.setEml(uuid);
            resume.setUsername(username);
            // System.out.println(resume);
            // ��Ӽ���
            resumeService.add(resume);
        }
    }
    
    /**
     * �鿴����
     * @param model
     * @return
     */
    @RequestMapping("check")
    public String check(Model model, @RequestParam(value="resume_id",required=false) String resume_id) {
        System.out.println("���ò鿴�����ӿ�");
        if(resume_id!=null){
            Default.resumeHtml = FileIO.read(Default.emlPath + resume_id + ".html", "utf-8");
        }else{
            System.out.println("resume_idΪ�գ�");
        }
        return "checkResume";
    }

}
