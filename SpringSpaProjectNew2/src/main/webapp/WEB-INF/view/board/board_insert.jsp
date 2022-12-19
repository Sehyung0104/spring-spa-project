<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src="js/board.js"></script>
</head>
<body>
<h2>게시판</h2>

<form class='frm frm_insert' method='post' enctype='mulitpart/form-data'>
	<label>작성자</label>
	<input type='text' name='id' value='hong'/><br/>
	
	<label>제목</label>
	<input type='text' name='subject' class='subject' value='제목'/><br/>
	<label></label>
	<textarea rows="5" cols="50" name="doc" class='doc'>내용입니다.</textarea>
	<input type='file' name='attFile' multiple="multiple"/>
	<div class='btnZone'>
		<input type='button' value='취소' class='btnSelect'>
		<input type='button' value='저장' class='btnInsertR'>
		<input type='text' name='nowPage' value='${pVo.nowPage }'>
		<input type='text' name='findStr' value='${pVo.findStr }'>
	</div>
</form>
</body>
</html>