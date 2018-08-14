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
 * 简历表接口雷
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
     * 上传文件
     * 
     * @param username
     * @return
     */
    @RequestMapping(value = "/import")
    @ResponseBody
    public boolean importing(HttpServletRequest request, @RequestParam(value="username",required=false) String username) {
        System.out.println("/* 调用导入文件数据接口 */");
        if (username == null) {
            System.out.println("用户名为空,使用默认用户名：" + Default.username);
            username = Default.username;
        }else{
            System.out.println("用户名：" + username);            
        }
        boolean bool = true;
        try{
            // 创建一个多分解的容器
            CommonsMultipartResolver cmr = new CommonsMultipartResolver(request
                    .getSession().getServletContext());
            // 设置编码
            cmr.setDefaultEncoding("utf-8");
            // 判断是否有文件上传
            if (cmr.isMultipart(request)) {
                // 将request转换成多分解请求
                MultipartHttpServletRequest mhs = cmr.resolveMultipart(request);
                // 根据input中存在的name来获取是否存在上传文件
                List<MultipartFile> mf = mhs.getFiles("file");// 也可以用getFile("file")的方式来处理单个文件,返回的是MultipartFile
                List<File> files = new ArrayList<File>();
                for (int i = 0; i < mf.size(); i++) {
                    //创建文件保存名
                    File file = new File(Default.emlPath + "buffer\\"+UUID.randomUUID().toString()+".eml");
                    // 上传文件
                    mf.get(i).transferTo(file);
                    //获取文件后缀名
                    String suffix = file.getName().substring(
                            file.getName().lastIndexOf(".") + 1);
                    if (suffix.equals("eml")) {
                        files.add(file);
                    }else{
                        System.out.println("导入的文件格式不对！！！");
                    }
                }
                DealWithFiles(files, username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("报错啦");
            bool = false;
        }
        return bool;
    }
    
    /**
     * 处理eml文件列表，解析文件内容，保持为简历列表List<Resume>并保存到数据库中
     * @param files
     * @param username
     * @throws Exception
     */
    private void DealWithFiles(List<File> files, String username) throws Exception {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            System.out.println("临时文件地址：" + file.getAbsolutePath());
            // 获取resume主键
            String uuid = UUID.randomUUID().toString();
            // 移动文件
            new CopyFile().moveFile(file.getAbsolutePath(), Default.emlPath
                    + uuid + ".eml");
            System.out.println("保存文件地址："+ Default.emlPath
                    + uuid + ".eml");
            // 获取文件内容
            String content = Eml.getContent(Default.emlPath + uuid + ".eml", uuid);
            // System.out.println(content);
            // 解析文件内容
            Resume resume = ResumeValue.get(content);
            resume.setResume_id(uuid);
            resume.setEml(uuid);
            resume.setUsername(username);
            // System.out.println(resume);
            // 添加简历
            resumeService.add(resume);
        }
    }
    
    /**
     * 查看简历
     * @param model
     * @return
     */
    @RequestMapping("check")
    public String check(Model model, @RequestParam(value="resume_id",required=false) String resume_id) {
        System.out.println("调用查看简历接口");
        if(resume_id!=null){
            Default.resumeHtml = FileIO.read(Default.emlPath + resume_id + ".html", "utf-8");
        }else{
            System.out.println("resume_id为空！");
        }
        return "checkResume";
    }

}
