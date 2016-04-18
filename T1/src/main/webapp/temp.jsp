<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
${param.msg}
=======
<c:if test="${!empty param.msg}">
	<script type="text/javascript">
		alert("${param.msg}");
		window.location.href = "${param.url}";
	</script>
</c:if>
<body>
</body>
</html>