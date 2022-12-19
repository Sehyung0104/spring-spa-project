/**
 * index.js
 */

$('#btnHome').on('click', function() {
	location.href="/";
});

$('#btnGuestBook').on('click', function() {
	$('.sectionInner').load('/guestbook/guestbook_select');
});

$('#btnBoard').on('click', function() {
	$('.sectionInner').load('/board/board_select'); 
});

/***** 최근 방명록 10개 *****/
/*$(document).ready(function() {
	$('.newGuestbook').load('/guestbook/guestbook10');
});*/

$('.newGuestbook').load('/guestbook/guestbook10');


/***** 최근 게시물 10개 *****/
$('.newBoard').load('/board/board10');
