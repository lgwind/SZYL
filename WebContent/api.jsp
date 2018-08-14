<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.szyl.util.IP" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>API</title>
</head>
<body>
<h1>API界面</h1>

<h3>简历表数据接口</h3>
获取所有简历：http://<%=IP.is() %>:8080/SZYL/resume/getAll<br>
无参数<br><br>
获取部分简历：http://<%=IP.is() %>:8080/SZYL/resume/getAllByUsername?username=username<br>
其中username为get请求的参数<br><br>
获取单个简历：http://<%=IP.is() %>:8080/SZYL/resume/get?resume_id=resume_id<br>
其中resume_id为get请求的参数<br><br>
添加简历：http://<%=IP.is() %>:8080/SZYL/resume/add<br>
参数：{"resume_id":"test","username":"test","name":"test","phone":"test","email":"test","school":"test","major":"test","position":"test","age":27,"state":"test","work_age":5,"skill":"test","eml":"test","update_time":"2018-07-20 00:00:00.0"}<br><br>
修改简历：http://<%=IP.is() %>:8080/SZYL/resume/update<br>
参数：{"resume_id":"test","username":"test","name":"test","phone":"test","email":"test","school":"test","major":"test","position":"test","age":27,"state":"test","work_age":5,"skill":"test","eml":"test","update_time":"2018-07-20 00:00:00.0"}<br><br>
删除简历：http://<%=IP.is() %>:8080/SZYL/resume/delete?resume_id=resume_id&username=username<br>
其中resume_id和username为get请求的参数<br><br>

<h3>excel文件下载接口</h3>
http://<%=IP.is() %>:8080/SZYL/xls/username.xls<br>
其中username为自动生成的用户名<br><br>

<h3>eml文件导入接口</h3>
http://<%=IP.is() %>:8080//SZYL/file/import<br>
参数：{"username":"test", "file"; file"}<br><br>

<h3>用户表数据接口</h3>
获取所有用户：http://<%=IP.is() %>:8080/SZYL/user/getAll<br>
无参数<br><br>
获取单个用户：http://<%=IP.is() %>:8080/SZYL/user/get?username=username<br>
其中username为get请求的参数<br><br>
添加用户：http://<%=IP.is() %>:8080/SZYL/user/add<br>
参数：{"username":"test","password":"test","role":"test"}<br><br>
修改用户：http://<%=IP.is() %>:8080/SZYL/user/update<br>
参数：{"username":"test","password":"test","role":"test"}<br><br>
删除用户：http://<%=IP.is() %>:8080/SZYL/user/delete?username=username<br>
其中username为get请求的参数<br><br>
</body>
</html>