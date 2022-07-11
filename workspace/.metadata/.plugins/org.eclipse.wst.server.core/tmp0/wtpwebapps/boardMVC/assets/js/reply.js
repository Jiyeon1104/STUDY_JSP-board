var replyList;
var check = false;

getList();

//댓글 목록
function getList(){
   $.ajax({
       // url: contextPath + "/board/BoardReplyListOk.bo?boardNumber=" + boardNumber,
       url: contextPath + "/board/BoardReplyListOk.bo",
       type: "get",
       // dataType: "text", //text일 경우 dataType 생략 가능
       dataType: "json",
       data: {"boardNumber": boardNumber},
       contentType: "application/json;charset=utf-8",
       success: showList,
       error: function(a, b, c){
           console.log("오류" + c);
       }
   });
}

//댓글 목록
function showList(replies){
	replyList = replies;
    var text = "";
    if(replies != null && replies.length != 0){
        $.each(replies, function(index, reply){
            text += "<div id='reply'>"
            text += "<p class='writer'>" + reply.memberId +"</p>"
            text += "<div class='content' id='content"+ index +"' style='width:100%'>"
            text += "<pre>" + reply.replyContent + "</pre>"
            text += "</div>"
            if(memberNumber == reply.memberNumber){
                text += "<input type='button' id='ready" + index + "' class='primary' value='수정' onclick='readyToUpdate(" + index + ")'>";
                text += "<input type='button' id='ok" + index + "' class='primary' style='display:none;' value='수정 완료' onclick='update(" + index +")'>";
                //버튼 마다 삭제할 댓글의 번호를 같이 전달한다.
                text += "<input type='button' id='remove" + index + "' class='primary' value='삭제' onclick='remove(" + reply.replyNumber + ")'>"
            }
            text += "</div>"
        });
    }else{
        //댓글 없음
        text = "<p>댓글이 없습니다.</p>";
    }

   $("#replies").html(text);
}

//댓글 등록
function regist(){
    var content = $("textarea#content");
    
    $.ajax({
        url: contextPath + "/board/BoardReplyWriteOk.bo",
        type: "post",
        data: {"boardNumber": boardNumber, "replyContent": content.val()},
        //post방식으로 전송할 때에는 contentType을 
        //"application/x-www-form-urlencoded; charset=UTF-8"로 설정해야 한다.
        //HttpServletRequest가 Header에 form데이터로 전송을 하기 때문이다.
        //따라서 post방식으로 위와 같은 데이터를 전달할 때에는 contentType을 생략해도 된다.
        //contentType을 생략하면 default값이 "application/x-www-form-urlencoded; charset=UTF-8"로 설정된다.
        /*contentType: "application/json;charset=utf-8",*/
        success: function(){
            content.val("");
            getList();
        },
        error: function(){
            console.log("오류");
        }
    });
}

//댓글 삭제
function remove(replyNumber){
    $.ajax({
        url: contextPath + "/board/BoardReplyDeleteOk.bo",
        type: 'get',
        data: {"replyNumber": replyNumber},
        success: function(){
            getList();
        },
        error: function(){
            console.log("오류");
        }
    });
}

//댓글 수정 버튼 클릭
function readyToUpdate(index){
	if(check){
		alert("이미 수정중인 댓글이 있습니다.");
		return;
	}
	var div = $("#content" + index);
	var updateReady = $("#ready" + index);
	var updateOk = $("#ok" + index);
	var remove = $("#remove" + index);
	console.log(remove);
	remove.replaceWith("<input type='button' id='cancel" + index +"' value='취소' onclick='updateCancel(" + index + ")'>");
	div.replaceWith("<textarea name='replyContent' id='content" + index +"' style='resize:none; border-radius:0; background:white'>" + div.text() + "</textarea>");
	
	check = true;
	
	updateReady.hide();
	updateOk.show();
}

//댓글 수정 취소
function updateCancel(index){
	
	check = false;
	
	var div = $("#content" + index);
	var updateReady = $("#ready" + index);
	var updateOk = $("#ok" + index);
	var cancel = $("#cancel" + index);
	
	updateReady.show();
	updateOk.hide();
	
	cancel.replaceWith("<input type='button' id='cancel" + index +"' value='삭제' onclick='remove(" + replyList[index].replyNumber + ")'>");
	div.replaceWith("<div class='content' id='content"+ index +"' style='width:100%'><pre>" + replyList[index].replyContent + "</pre></div>");	
}

function update(index){
	var textarea = $("#content" + index);
	
	$.ajax({
		url: contextPath + "/board/BoardReplyUpdateOk.bo",
		type: 'post',
		data: {"replyNumber": replyList[index].replyNumber, "replyContent": textarea.val()},
		success: function(){
			getList();
			check = false;
		},
        error: function(){
            console.log("오류");
        }
	});
	
}

