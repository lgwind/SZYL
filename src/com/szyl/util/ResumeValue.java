package com.szyl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.szyl.entity.Resume;

public class ResumeValue {

    public static Resume get(String eml) {
        Resume resume = new Resume();
        try {
            if (eml.indexOf("| 男 |") != -1) {
                resume.setName(getBefore(eml, "| 男 |"));
            } else if (eml.indexOf("| 女 |") != -1) {
                resume.setName(getBefore(eml, "| 女 |"));
            }
            resume.setPhone(getLater(eml, "手机："));
            resume.setEmail(getLater(eml, "\r\n\r\n邮箱：\r\n\r\n"));
            resume.setSchool(getLater(eml, "学校："));
            resume.setMajor(getLater(eml, "专业："));
            resume.setPosition(getLater(eml, "应聘职位："));
            if (eml.indexOf("| 男 |") != -1) {
                resume.setAge(Integer.parseInt(getBetween(eml, "| 男 |", "岁")));
            }else if (eml.indexOf("| 女 |") != -1) {
                resume.setAge(Integer.parseInt(getBetween(eml, "| 女 |", "岁")));
            }
            resume.setState(getLater(eml, "求职状态："));
            resume.setWork_age(Integer.parseInt(getBetween(eml, ") |", "年工作经验")));
            resume.setSkill(KeyWord.find(eml));
            resume.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resume);
        return resume;        
    }

    /**
     * 在字符串eml中寻找 \n字符串 到 find字符串 之间的非空字符串
     * 
     * @param eml
     * @param find
     * @return
     */
    private static String getBefore(String eml, String find) {
        int num = eml.indexOf(find);
        String str = eml.substring(num - 10, num);
        String[] strs = str.split("\n");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].replace(" ", "");
            strs[i] = strs[i].replace("\r", "");
        }
        for (int i = strs.length - 1; i > -1; i--) {
            if (!strs[i].equals("")) {
                str = strs[i];
                System.out.println("寻获字符串:" + str);
                break;
            }
        }
        return str;
    }

    /**
     * 在字符串eml中寻找 find字符串 到 \n字符串 之间的非空字符串
     * 
     * @param eml
     * @param find
     * @return
     */
    private static String getLater(String eml, String find) {
        int num = eml.indexOf(find) + find.length();
        String str = eml.substring(num);
        String[] strs = str.split("\n");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].replace(" ", "");
            strs[i] = strs[i].replace("\r", "");
        }
        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].equals("")) {
                str = strs[i];
                System.out.println("寻获字符串:" + str);
                break;
            }
        }
        return str;
    }

    /**
     * 在字符串eml中寻找 find字符串 到 find2字符串 之间的非空字符串
     * 
     * @param eml
     * @param find
     * @return
     */
    private static String getBetween(String eml, String find, String find2) {
        int num = eml.indexOf(find) + find.length();
        int num2 = eml.indexOf(find2);
        if(num>=num2){
            System.out.println("找不到字符串!");
            return "";            
        }
        String str = eml.substring(num, num2);
        str = str.replace(" ", "");
        System.out.println("寻获字符串:" + str);
        return str;
    }

    public static void main(String[] args) {
      //测试用的变量
//        String eml = Eml.getContent("D://LinuxWorkspace//SZYL//WebContent//eml//无.eml");
//        get(eml);
    }

}
