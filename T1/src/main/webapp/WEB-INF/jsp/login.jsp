<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/saveLogin.do">

	帐号:<input name="account">
	密码:<input name="password">
	<input type="hidden" name="token" value="${loginToken}">
	<input type="submit" value="提交">
</form>
</body>
</html>