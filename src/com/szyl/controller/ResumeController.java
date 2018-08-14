package com.szyl.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szyl.entity.Resume;
import com.szyl.service.ResumeService;
import com.szyl.util.CopyFile;
import com.szyl.util.Default;
import com.szyl.util.Excel;

/**
 * 简历表接口雷
 * 
 * @author chennaile
 *
 */
@Controller
@RequestMapping("/resume")
public class ResumeController {

    private ResumeService resumeService = new ResumeService();

    /**
     * 添加简历
     * 
     * @param resume
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add(Resume resume) {
        //若主键为空
        if(resume.getResume_id()==null){
            String uuid = UUID.randomUUID().toString();
            resume.setResume_id(uuid);
        }
        //eml文件地址为空
        if(resume.getEml()==null){
            resume.setEml(resume.getResume_id());
        }
        //若用户名为空
        if(resume.getUsername()==null){
            resume.setUsername(Default.username);
        }
        return resumeService.add(resume);
    }

    /**
     * 修改简历
     * 
     * @param resume
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public int update(Resume resume) {
        return resumeService.add(resume);
    }

    /**
     * 删除简历
     * 
     * @param resume_id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam(value="resume_id",required=false) String resume_id,
            @RequestParam(value="username",required=false) String username) {
        int returnNum = 0;
        if(resume_id!=null){
            try{
                returnNum += resumeService.delete(resume_id);
                //删除静态文件
                new CopyFile().delFile(Default.emlPath + resume_id + ".eml");
                new CopyFile().delFile(Default.emlPath + resume_id + ".html");
                System.out.println("删除文件成功："+resume_id+".eml");
                System.out.println("删除文件成功："+resume_id+".html");
            }catch(Exception e){
                System.out.println("删除文件失败："+resume_id+".eml");
                System.out.println("删除文件失败："+resume_id+".html");
            }
        }
        if(username!=null){
            try{
                List<Resume> list = resumeService.getAll(username);
                for (int i = 0; i < list.size(); i++) {
                    String id = list.get(i).getResume_id();
                    returnNum += resumeService.delete(id);
                    // 删除静态文件
                    new CopyFile().delFile(Default.emlPath + id + ".eml");
                    new CopyFile().delFile(Default.emlPath + id + ".html");
                    System.out.println("删除文件成功：" + id + ".eml");
                    System.out.println("删除文件成功："+resume_id+".html");
                }
            }catch(Exception e){
                System.out.println("删除文件失败："+resume_id+".eml");
                System.out.println("删除文件失败："+resume_id+".html");
            }
        }
        return returnNum;
    }

    /**
     * 获取简历（根据简历id）
     * 
     * @param resume_id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Resume get(@RequestParam(value="resume_id",required=false) String resume_id) {
        return resumeService.get(resume_id);
    }

    /**
     * 获取所有简历
     * 
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Resume> getAll() {
        List<Resume> list = resumeService.getAll();
        //导出excel表格
        excel(list, Default.username);
        return list;
    }

    /**
     * 获取所有简历
     * 
     * @return
     */
    @RequestMapping("/getAllByUsername")
    @ResponseBody
    public List<Resume> getAll(@RequestParam(value="username",required=false) String username) {
        if(username==null){
            username = Default.username;
        }
        List<Resume> list = resumeService.getAll(username);
        //导出excel表格
        excel(list, username);
        return list;
    }
    
    /**
     * 导出excel表格
     * @param list
     * @param xlsname
     */
    private void excel(List<Resume> list, String xlsname){
      //导出excel表格
        try {
            Excel excel = new Excel();            
            excel.newXLS(Default.xlsPath + xlsname + ".xls");
            String[] rowTitle = { "序号", "姓名", "手机", "邮件", "学校", "专业", "申请职位", "年龄",
                    "在职状态", "工作年限", "技能关键词" };
            // 写入一行数据
            excel.writeXLS(rowTitle);
            for (int i = 0; i < list.size(); i++) {
                Resume resume = list.get(i);
                String[] row = {(i+1)+"", ""+resume.getName(), ""+resume.getPhone(),
                        ""+resume.getEmail(), ""+resume.getSchool(),
                        ""+resume.getMajor(), ""+resume.getPosition(),
                        "" + resume.getAge(), ""+resume.getState(),
                        "" + resume.getWork_age(), ""+resume.getSkill() };
                excel.writeXLS(row);
            }
            excel.writeclose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
