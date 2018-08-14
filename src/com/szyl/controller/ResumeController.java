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
 * ������ӿ���
 * 
 * @author chennaile
 *
 */
@Controller
@RequestMapping("/resume")
public class ResumeController {

    private ResumeService resumeService = new ResumeService();

    /**
     * ��Ӽ���
     * 
     * @param resume
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int add(Resume resume) {
        //������Ϊ��
        if(resume.getResume_id()==null){
            String uuid = UUID.randomUUID().toString();
            resume.setResume_id(uuid);
        }
        //eml�ļ���ַΪ��
        if(resume.getEml()==null){
            resume.setEml(resume.getResume_id());
        }
        //���û���Ϊ��
        if(resume.getUsername()==null){
            resume.setUsername(Default.username);
        }
        return resumeService.add(resume);
    }

    /**
     * �޸ļ���
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
     * ɾ������
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
                //ɾ����̬�ļ�
                new CopyFile().delFile(Default.emlPath + resume_id + ".eml");
                new CopyFile().delFile(Default.emlPath + resume_id + ".html");
                System.out.println("ɾ���ļ��ɹ���"+resume_id+".eml");
                System.out.println("ɾ���ļ��ɹ���"+resume_id+".html");
            }catch(Exception e){
                System.out.println("ɾ���ļ�ʧ�ܣ�"+resume_id+".eml");
                System.out.println("ɾ���ļ�ʧ�ܣ�"+resume_id+".html");
            }
        }
        if(username!=null){
            try{
                List<Resume> list = resumeService.getAll(username);
                for (int i = 0; i < list.size(); i++) {
                    String id = list.get(i).getResume_id();
                    returnNum += resumeService.delete(id);
                    // ɾ����̬�ļ�
                    new CopyFile().delFile(Default.emlPath + id + ".eml");
                    new CopyFile().delFile(Default.emlPath + id + ".html");
                    System.out.println("ɾ���ļ��ɹ���" + id + ".eml");
                    System.out.println("ɾ���ļ��ɹ���"+resume_id+".html");
                }
            }catch(Exception e){
                System.out.println("ɾ���ļ�ʧ�ܣ�"+resume_id+".eml");
                System.out.println("ɾ���ļ�ʧ�ܣ�"+resume_id+".html");
            }
        }
        return returnNum;
    }

    /**
     * ��ȡ���������ݼ���id��
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
     * ��ȡ���м���
     * 
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Resume> getAll() {
        List<Resume> list = resumeService.getAll();
        //����excel���
        excel(list, Default.username);
        return list;
    }

    /**
     * ��ȡ���м���
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
        //����excel���
        excel(list, username);
        return list;
    }
    
    /**
     * ����excel���
     * @param list
     * @param xlsname
     */
    private void excel(List<Resume> list, String xlsname){
      //����excel���
        try {
            Excel excel = new Excel();            
            excel.newXLS(Default.xlsPath + xlsname + ".xls");
            String[] rowTitle = { "���", "����", "�ֻ�", "�ʼ�", "ѧУ", "רҵ", "����ְλ", "����",
                    "��ְ״̬", "��������", "���ܹؼ���" };
            // д��һ������
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
