<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WEB-INF/guestbook/guestbook_insert.jsp</title>
</head>
<body>

<div class="item" id="item_insert">
	<form class="frm_css frm_guestbook_insert" method="post" enctype="multipart/form-data">
		<label>작성자</label>
		<input type="text" name="id" value="insert"/>
		<label>작성일</label>
		<output name="nal">
			<fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd"/>
		</output>
		<input type="button" value="CSS" style="visibility: hidden;"/>
		<br/>
		<textarea rows="5" cols="40" name="doc" class="doc">insert</textarea>
		<br/>
		<label style="margin-right: 1px;">암호</label>
		<input type="password" name="pwd" autocomplete="off"/>
		<input type="button" value="CSS" style="visibility: hidden;"/>
		<input type="button" value="CSS" style="visibility: hidden;"/>
		<input type="button" value="작성" class="btnGuestbookSave">
	</form>
</div>

</body>
</html>