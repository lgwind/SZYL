package com.szyl.util;

import org.apache.commons.lang.StringUtils;

public class Default {
    
    /**未提交用户名时的默认用户名*/
    public static String username = "none";
    
//    private static String bathPath = "D:\\LinuxWorkspace\\";
    private static String basePath = getBasePath();
    
    private static String getBasePath(){
        // 获取相对路径
        String path = Default.class.getClassLoader().getResource("")
                .getPath();// 获取工程classes下的路径
        int nIndex = path.indexOf("WEB-INF");
        path = StringUtils.substring(path, 0, nIndex);
        return path;
    }
    /**xls文件保存路径*/
//    public static String xlsPath = bathPath + "SZYL\\WebContent\\xls\\";
    public static String xlsPath = basePath + "xls\\";
    
    /**eml文件保存路径*/
    public static String emlPath = basePath + "eml\\";
    
    public static String resumeHtml = "";

}
