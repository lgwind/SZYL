package com.szyl.util;

import org.apache.commons.lang.StringUtils;

public class Default {
    
    /**δ�ύ�û���ʱ��Ĭ���û���*/
    public static String username = "none";
    
//    private static String bathPath = "D:\\LinuxWorkspace\\";
    private static String basePath = getBasePath();
    
    private static String getBasePath(){
        // ��ȡ���·��
        String path = Default.class.getClassLoader().getResource("")
                .getPath();// ��ȡ����classes�µ�·��
        int nIndex = path.indexOf("WEB-INF");
        path = StringUtils.substring(path, 0, nIndex);
        return path;
    }
    /**xls�ļ�����·��*/
//    public static String xlsPath = bathPath + "SZYL\\WebContent\\xls\\";
    public static String xlsPath = basePath + "xls\\";
    
    /**eml�ļ�����·��*/
    public static String emlPath = basePath + "eml\\";
    
    public static String resumeHtml = "";

}
