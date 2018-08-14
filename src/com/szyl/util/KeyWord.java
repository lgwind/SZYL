package com.szyl.util;

import java.util.ArrayList;
import java.util.List;

public class KeyWord {
    
    public static String[] keyWords = {"Java开发","Web前端",".NET开发","WebGIS开发","Android开发",
        "Spring","Hibernater","MyBatis","Spring MVC","Spring Boot","GeoGlobe Server","Nignx","Node.js",
        "HTML5","CSS+DIV","JavaScript","bootstrap","JQuery","Echart","VUE","LigerUI","Sea.js","LayUI",
        "ASP.NET","ADO.NET","OSGI.NET","Dapper",".NET MVC",
        "ArcGIS Server SOE","SOI GpServer","ArcGIS Server SOE Node.js",
        "ArcGis API for Javascript","Dojo","GeoGlobe MapBox","Cesium","D3","DeckGL"
            };
    
    private static List<String> findList(String str){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<keyWords.length; i++){
            if(str.indexOf(keyWords[i])!=-1){
                list.add(keyWords[i]);
            }
        }
        return list;
    }
    
    public static String find(String str){
        String keyWords = findList(str).toString();
        keyWords = keyWords.replace("[", "");
        keyWords = keyWords.replace("]", "");
        keyWords = keyWords.replace(", ", "、");
        return keyWords;
    }

}
