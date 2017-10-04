<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!-- 여기서부터 jsp 소스넣기  -->
<script src="<c:url value='/resources/setting.js'/>"/>
<script type="text/javascript">
	function commentChk() {
		f = document.commentform;
		if(!f.content.value || f.content.value.trim().length == 0) {
			alert('내용넣어!!!');
			f.content.value = "";
			f.content.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form method="post" name="form1">
	이름 : <input type="text" name="name" value="${vo.board_writer}" readonly="readonly"/><br/>
	작성일 : <input type="text" name="wdate" value="${vo.board_regdate}" readonly="readonly"/><br/>
	제목 : <input type="text" name="title" size="80" value="${vo.board_title}" readonly="readonly"/><br/>
	내용 : <textarea rows="10" cols="80" name="content" readonly="readonly">${vo.board_content}</textarea><br/>
</form>
<form action="reply?pageNo=${pageNo}&board_no=${vo.board_no}" method="post">
	<input type="hidden" name="board_start_ref" value="${vo.board_start_ref}"/>
	<input type="hidden" name="board_lev" value="${vo.board_lev}"/>
	<input type="hidden" name="board_seq" value="${vo.board_seq}"/>
	<input type="button" onclick="location.href='list?pageNo=${pageNo}'" value="리스트로"/>
	<input type="submit" value="답변달기"/>
	<c:if test="${userVo.user_id == vo.user_id}">
		<input type="button" onclick="location.href='update?pageNo=${pageNo}&board_no=${vo.board_no}'" value="수정하기"/>
		<input type="button" onclick="location.href='delete?pageNo=${pageNo}&board_no=${vo.board_no}'" value="삭제하기"/>
	</c:if>
	<br/>
</form>
<!--  댓글폼 -->
<form method="post" name="commentform" action="commentOK">
	<input type="hidden" name="idx" value="${vo.board_no}"/>   
	<input type="hidden" name="ref" value="${vo.board_no}"/>   
	<input type="hidden" name="pageNo" value="${pageNo}"/>
	<input type="hidden" name="mode" value="1"/>
	<input type="hidden" name="ip" value="${pageContext.request.remoteAddr}"/>
	
	내용 : <textarea rows="3" cols="50" name="content"></textarea> <br/>
	
	<input type="submit" value="댓글달기" name="commentBtn" onclick="return commentChk();"/>
	
<!-- 댓글 리스트 -->
	<c:if test="${comment.list.size() == 0}">
		<div width="90%" style="border:1px solid gray;">
			등록된 댓글이 없습니다.
		</div>
	</c:if>
	<c:if test="${comment.list.size() > 0}">
		<div width="90%" style="border:1px solid gray;">
		<c:forEach var="co" items="${comment.list}">
			<div width="95%" style="border:1px solid silver;">
			<div width="90%" style="background-color:silver">
				이름: ${co.name} (${co.wdate})
			<c:set var="content" value="${co.content}"/>
			<c:set var="content" value="${fn:replace(content, '>', '&gt;')}"/>
			<c:set var="content" value="${fn:replace(content, '<', '&lt;')}"/>
			<c:set var="rn" value="${rn}"/>
			<c:set var="content" value="${fn:replace(content, rn, '<br/>')}"/>
			<input type="button" value="수정" onclick="setting(2, '${co.idx}', '수정', '${co.name}', '${content}')"/>
			<input type="button" value="삭제" onclick="setting(3, '${co.idx}', '삭제', '${co.name}', '${content}')"/>
			</div>
			${content}
			</div>
		</c:forEach>
		</div>
	</c:if>		
</form>
<%@ include file="/WEB-INF/include/footer.jsp" %>