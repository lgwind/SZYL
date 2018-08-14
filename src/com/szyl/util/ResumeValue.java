package com.szyl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.szyl.entity.Resume;

public class ResumeValue {

    public static Resume get(String eml) {
        Resume resume = new Resume();
        try {
            if (eml.indexOf("| �� |") != -1) {
                resume.setName(getBefore(eml, "| �� |"));
            } else if (eml.indexOf("| Ů |") != -1) {
                resume.setName(getBefore(eml, "| Ů |"));
            }
            resume.setPhone(getLater(eml, "�ֻ���"));
            resume.setEmail(getLater(eml, "\r\n\r\n���䣺\r\n\r\n"));
            resume.setSchool(getLater(eml, "ѧУ��"));
            resume.setMajor(getLater(eml, "רҵ��"));
            resume.setPosition(getLater(eml, "ӦƸְλ��"));
            if (eml.indexOf("| �� |") != -1) {
                resume.setAge(Integer.parseInt(getBetween(eml, "| �� |", "��")));
            }else if (eml.indexOf("| Ů |") != -1) {
                resume.setAge(Integer.parseInt(getBetween(eml, "| Ů |", "��")));
            }
            resume.setState(getLater(eml, "��ְ״̬��"));
            resume.setWork_age(Integer.parseInt(getBetween(eml, ") |", "�깤������")));
            resume.setSkill(KeyWord.find(eml));
            resume.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resume);
        return resume;        
    }

    /**
     * ���ַ���eml��Ѱ�� \n�ַ��� �� find�ַ��� ֮��ķǿ��ַ���
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
                System.out.println("Ѱ���ַ���:" + str);
                break;
            }
        }
        return str;
    }

    /**
     * ���ַ���eml��Ѱ�� find�ַ��� �� \n�ַ��� ֮��ķǿ��ַ���
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
                System.out.println("Ѱ���ַ���:" + str);
                break;
            }
        }
        return str;
    }

    /**
     * ���ַ���eml��Ѱ�� find�ַ��� �� find2�ַ��� ֮��ķǿ��ַ���
     * 
     * @param eml
     * @param find
     * @return
     */
    private static String getBetween(String eml, String find, String find2) {
        int num = eml.indexOf(find) + find.length();
        int num2 = eml.indexOf(find2);
        if(num>=num2){
            System.out.println("�Ҳ����ַ���!");
            return "";            
        }
        String str = eml.substring(num, num2);
        str = str.replace(" ", "");
        System.out.println("Ѱ���ַ���:" + str);
        return str;
    }

    public static void main(String[] args) {
      //�����õı���
//        String eml = Eml.getContent("D://LinuxWorkspace//SZYL//WebContent//eml//��.eml");
//        get(eml);
    }

}
