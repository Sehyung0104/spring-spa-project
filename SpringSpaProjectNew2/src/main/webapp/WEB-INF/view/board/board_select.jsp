<%@page import="com.kang.board.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- META -->
<meta charset="UTF-8">
<!-- CSS -->
<link rel="stylesheet" href="css/board.css">
<!-- JS -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src="js/board.js"></script>
<title>WEB-INF/view/board/board_select.jsp</title>
</head>
<body>
<!-- CONTENT -->
<div id='main2'>
    <div id='content2'>
		<!-- DOC -->
		<div id='board2'>
			<form class='frm_search2' method='post'>
				<input type='hidden' name='nowPage' value='${pVo.nowPage }'/>
				<input type='hidden' name='sno' value='${pVo.sno }'/>
				<input type='button' value='입력' class='btnInsert'/>
				<input type='search' autocomplete='off' name='findStr' size='40' value='${pVo.findStr }'/>
				<input type='button' value='조회' class='btnSearch'/>
			</form>
			<div class='title2'>
				<span class='no2'>NO</span>
				<span class='subject2'>제목</span>
				<span class='id2'>작성자</span>
				<span class='nal2'>작성일</span>
				<span class='hit2'>조회수</span>
			</div>
			<div class='items2'>
				<c:forEach var='vo' items='${list }'>
					<div class='item2' onclick="view(${vo.subject eq '삭제된 글입니다.' ? '0' : vo.sno })">
						<span class='no2'>${vo.sno }</span>
						<span class='subject2'> ${vo.subject } 
							<c:if test='${vo.attCnt>0 }'>
								(첨부:${vo.attCnt })
							</c:if>
						</span>
						<span class='id2'>${vo.id }</span>
						<span class='nal2'>${vo.nal }</span>
						<span class='hit2'>${vo.hit }</span>
					</div>
				</c:forEach>
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
    </div>
</div>
</body>
</html>