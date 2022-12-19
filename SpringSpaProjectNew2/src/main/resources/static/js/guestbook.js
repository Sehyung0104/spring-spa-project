/**
 * guestbook.js
 */

$('#btnFindStr').on('click', function() {
	var frm = $('.frm_guestbook_search')[0];
	frm.nowPage.value = 1;
	
	var param = $(frm).serialize();
	$('.sectionInner').load('/guestbook/guestbook_select', param);
});

/*function movePage(nowPage) {
	var frm = $('.frm_guestbook_search')[0];
	frm.nowPage.value = nowPage;
	
	var param = $(frm).serialize();
	$('.sectionInner').load('/guestbook/guestbook_select', param);
//	$.post('/guestbook/guestbook_select', param, function(data) {
//		$('.sectionInner').html(data);
//	});
}*/

movePage = function(nowPage) {
	var frm = $('.frm_guestbook_search')[0];
	frm.nowPage.value = nowPage;
	var param = $(frm).serialize();
	
	$.ajax({
		url: '/guestbook/guestbook_select',
		method: 'post',
		data: param,
		success: function(koko) {
			$('.sectionInner').html(koko);
		}
	});
}

$('.btnGuestbookSave').on('click', function() {
	var frm = $('.frm_guestbook_insert')[0];
	var param = $(frm).serialize();
	$.post('/guestbook/guestbook_insert', param, function(msg) {
		if(msg != '') alert(msg);
		
		frm = $('.frm_guestbook_search')[0];
		param = $(frm).serialize();
		$('.sectionInner').load('/guestbook/guestbook_select', param);
	});
});

var docRepository;
var cnt = 0;
modifyView = function(frm) {
	if(cnt != 0) {
		alert('한번에 하나의 글만 수정할 수 있습니다.');
		return;
	} else {
		docRepository = frm.doc.value;
		var div = frm.querySelector('#pwdZone');
		div.style.visibility = 'visible';
		var textarea = frm.doc;
		$(textarea).prop('readOnly', false);
		$(frm.doc).css({
			'border': '2px solid blue',
			'box-sizing': 'border-box'
		});
		cnt++;
	}
}

modifyCancel = function(frm) {
	frm.doc.value = docRepository;
	var div = frm.querySelector('#pwdZone');
	div.style.visibility = 'hidden';
	var textarea = frm.doc;
	$(textarea).prop('readOnly', true);
	$(frm.doc).css({
		'border': ''
	});
	cnt = 0;
}

/***** modal box *****/
$('#btnClose').on('click', function() {
	$('#modal').css('display', 'none');
});

var delForm;
modalView = function(frm) {
	delForm = frm;
	$('#modal').css('display', 'block');
}

/***** 방명록 삭제 *****/
$('.btnCheck').on('click', function() {
	delForm.pwd.value = $('#pwdCheck').val();
	
	var param = $(delForm).serialize();
	$.post('/guestbook/guestbook_delete', param, function(msg) {
		if(msg != '') alert(msg);
		
		frm = $('.frm_guestbook_search')[0];
		param = $(frm).serialize();
		$('.sectionInner').load('/guestbook/guestbook_select', param);
	});
});

/***** 방명록 수정 *****/
update = function(frm) {
	var param = $(frm).serialize();
	$.post('/guestbook/guestbook_update', param, function(msg) {
		if(msg != '') alert(msg);
		
		frm = $('.frm_guestbook_search')[0];
		param = $(frm).serialize();
		$('.sectionInner').load('/guestbook/guestbook_select', param);
	});
}