<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到注册页面</title>
</head>
<body>
开始注册菜单

<form action="/saveRegistered.do">

帐号:<input type="text" name="account">
密码:<input type="text" name="password">
token:<input type="text" name="token" value="${token}">

<input type="submit" value="提交">
</form>
</body>
</html>