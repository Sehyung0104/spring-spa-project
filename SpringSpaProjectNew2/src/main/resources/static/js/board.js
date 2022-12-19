/**
 * 
 */
$('.btnSearch').on('click', function() {
	var frm = $('.frm_search2')[0];
	frm.nowPage.value = 1;
	var param = $(frm).serialize();
	$('.sectionInner').load('/board/board_select', param);
});

movePage = function(nowPage) {
	var frm = $('.frm_search2')[0];
	frm.nowPage.value = nowPage;
	var param = $(frm).serialize();
	$('.sectionInner').load('/board/board_select', param);
}

view = function(sno) {
	var frm = $('.frm_search2')[0];
	frm.sno.value = sno;
	var param = $(frm).serialize();
	$('.sectionInner').load('/board/board_view', param);
}

$('.btnSelect').on('click', function() {
	var frm = $('.frm_view')[0];
	var param = $(frm).serialize();
	$('.sectionInner').load('/board/board_select', param);
});



/*$('.btnDeleteR').on('click', function() {
	var frm = $('.frm_view')[0];
	var param = 
});
*/

 (board = function(){
    var frm = $('.frm')[0];
    var param;
    var url='board/board_main.jsp?';
    
    $('.btnSearch').on('click', function(){
        frm = $('.frm_search')[0];
        frm.nowPage.value = 1;
        param = $(frm).serialize();
        $.post(url + "job=select", param, function(data){
            $('#content').html(data);
        })
       
    })
    
    
    $('.btnInsert').on('click', function(){
        param = $('.frm_search').serialize();
        $.post("/board/board_insert", param, function(data){
            $('.sectionInner').html(data);
        })
    })
    $('.btnInsertR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
        
        $.ajax({
            type : 'POST',
            url : 'board/board_insertR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
				if(data=="fail"){
					alert("저장중 오류 발생");
				}
				frm.enctype='';
				param= $(frm).serialize();
				$('.sectionInner').load('/board/board_select', param);
            }
        })
    })
    
    
    $('.btnUpdate').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_update", param, function(data){
            $('.sectionInner').html(data);
        })
    })
    $('.btnUpdateR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : 'board/board_updateR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
				console.log(data);
                $('.sectionInner').html(data);
            }
        })
    })
    
    $('.btnSelect').on('click', function(){
        param = $(frm).serialize();
        $.post(url+ "job=select", param, function(data){
            $('#content').html(data);
        })
    })
    $('.btnRepl').on('click', function(){
        param = $(frm).serialize();
        $.post("/board/board_repl", param, function(data){
            $('.sectionInner').html(data);
        })
    })
    $('.btnReplR').on('click', function(){
        var frm = $('.frm')[0];
        var param = new FormData(frm);
       
        $.ajax({
            type : 'POST',
            url : '/board/board_replR',
            contentType : false,
            processData : false,
            data : param,
            dataType : 'html',
            success : function(data){
                $('.sectionInner').html(data);
            }
        })
    })
    $('.btnDeleteR').on('click', function(){
        let yn = confirm('자료를 삭제하시겠습니까 ?');
        if( !yn ) return;
       
        param = $('.frm').serialize();
        $.post("board/board_delete", param, function(data){
            $('.sectionInner').html(data);
        })
    })



    
    /*-------------------------------------------------------*/
   $('.btnRepl').on('click', function(){
       param = $(frm).serialize();
       $.post(url+ "job=repl", param, function(data){
           $('#section').html(data);
       })
   })
   
   /*-------------------------------------------------------*/
   $('.btnReplR').on('click', function(){
       var frm = $('.frm')[0];
       var param = new FormData(frm);
      
       $.ajax({
           type : 'POST',
           url : 'board/fileUpload.do?job=replR',
           contentType : false,
           processData : false,
           data : param,
           dataType : 'html',
           success : function(data){
               $('#section').html(data);
           }
       })
   })
   
   /*-------------------------------------------------------*/
})()
