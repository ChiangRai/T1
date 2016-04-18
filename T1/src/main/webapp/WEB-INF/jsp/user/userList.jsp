<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/js/pagingUtil.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 这里是集合
<form id="from" name="form1" method="post" action="/user_list.do">
    	<input name="page" id="page" type="hidden">
        
        </form>
<table >
	<tr>
		<td>帐号</td>
	</tr>
	
	<tr>
		<c:forEach items="${pageInfo}" var="user">
		<td>${user.account}</td>
		</c:forEach>
	</tr>
</table>
 <!--上一页 下一页-->
         <div class="page">
            	<c:if test="${pageInfo.getPageNum()>1}">
            	<a href="javascript:previousPage(${pageInfo.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${pageInfo.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${pageInfo.getPages()}">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${pageInfo.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${pageInfo.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${pageInfo.getPages()-3}">
            			<c:if test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${pageInfo.getPageNum()<pageInfo.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${pageInfo.getPages()-2}" end="${pageInfo.getPages()}">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${pageInfo.getPages()>pageInfo.getPageNum()}"><a href="javascript:previousPage(${pageInfo.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${pageInfo.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${pageInfo.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	

</body>
</html>