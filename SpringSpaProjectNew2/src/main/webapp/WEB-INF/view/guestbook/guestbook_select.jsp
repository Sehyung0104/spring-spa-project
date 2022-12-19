<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- META -->
<meta charset="UTF-8">
<!-- CSS -->
<link rel="stylesheet" href="css/guestbook.css">
<!-- JS -->
<script defer src="js/guestbook.js"></script>
<title>WEB-INF/guestbook/guestbook_select.jsp</title>
</head>
<body>
<!-- CONTENT -->
<div id="guestbook_list" style="width: 793px !important; margin: auto;">
	<!-- 정적삽입 -->
	<%@include file="guestbook_insert.jsp" %>
	<!-- SEARCH -->
	<form class="frm_guestbook_search" method="post">
		<input type="search" name="findStr" value="${pVo.findStr }" autocomplete="off"/>
		<input type="button" name="btnFindStr" id="btnFindStr" class="btn btn-outline-light" value="검색" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;"/>
		<input type="hidden" name="nowPage" value="${pVo.nowPage }"/>
	</form>
	<!-- DOC -->
	<div class="guestbook_items">
		<c:forEach var="vo" items="${list }">
			<div class="item">
				<form class="frm_css frm_guestbook" method="post" enctype="multipart/form-data">
					<label>작성자</label>
					<input type="text" name="id" value="${vo.id }" readOnly/>
					<label>작성일</label>
					<output name="nal">${vo.nal }</output>
					<input type="button" value="삭제" class="btnGuestbookDelete" onclick="modalView(this.form)">
					<br/>
					<textarea rows="5" cols="40" name="doc" class="doc" readOnly>${vo.doc }</textarea>
					<br/>
					<div id="pwdZone">
						<label class="label_pwd">암호</label>
						<input type="password" name="pwd" autocomplete="off"/>
						<input type="button" value="확인" class="btnGuestbookUpdateR" onclick="update(this.form)">
						<input type="button" value="취소" class="btnGuestbookCancel" onclick="modifyCancel(this.form)">
					</div>
					<input type="button" value="수정" class="btnGuestbookUpdate" onclick="modifyView(this.form)">
					<input type="hidden" name="sno" value="${vo.sno }"/>
				</form>
			</div>
		</c:forEach>
	</div>
</div>
<div id="modal">
	<div id="content">
		<label class="label_pwd">삭제하려면 암호를 입력해주세요.</label>
		<br/>
		<input type="password" id="pwdCheck" autocomplete="off"/>
		<input type="button" value="확인" class="btnCheck">
		<input type="button" id="btnClose" value="X"/>
	</div>
</div>
<!-- PAGE BUTTON -->
<div class="btn-toolbar" style="justify-content: center; border-top: 2px solid rgb(27, 84, 75); margin-top: 10px; padding-top: 10px;" role="toolbar" aria-label="Toolbar with button groups">
	<c:if test="${pVo.startPage > 1 }">
		<div class="btn-group me-2" role="group" aria-label="First group">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;" onclick="movePage(1)">처음</button>
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;" onclick="movePage(${pVo.startPage - 1 })"><</button>
		</div>
	</c:if>
	<div class="btn-group me-2" role="group" aria-label="Second group">
		<c:forEach var="i" begin="${pVo.startPage }" end="${pVo.endPage }" step="1">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;" onclick="movePage(${i })">${i }</button>
		</c:forEach>
	</div>
	<c:if test="${pVo.endPage lt pVo.totPage }">
		<div class="btn-group" role="group" aria-label="Third group">
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;" onclick="movePage(${pVo.endPage + 1 })">></button>
			<button type="button" class="btn btn-outline-light" style="background-color: rgb(164, 32, 42); font-family: Gil Sans; font-weight: 600;" onclick="movePage(${pVo.totPage })">맨끝</button>
		</div>
	</c:if>
</div>
</body>
</html>