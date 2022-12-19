<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB-INF/view/board/board_10.jsp</title>
</head>
<body>

<div style="color: white; background-color: #a4202a;">최신 게시물</div>
<c:forEach var="vo" items="${list }">
	<ul style="background-color: #ccc; border-top: 1px solid black; border-bottom: 1px solid black;">
		<li>작성자 : ${vo.id } | 작성일 : ${vo.nal }</li>
		<li>${vo.subject }</li>
	</ul>
</c:forEach>

</body>
</html>