package com.boardMVC.app.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardReplyDAO;
import com.boardMVC.app.board.vo.BoardReplyVO;

public class BoardReplyWriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		BoardReplyVO reply = new BoardReplyVO();
		BoardReplyDAO dao = new BoardReplyDAO();
		
		reply.setReplyContent(req.getParameter("replyContent"));
		reply.setBoardNumber(Integer.parseInt(req.getParameter("boardNumber")));
		reply.setMemberNumber((Integer)req.getSession().getAttribute("memberNumber"));
		
		dao.insertReply(reply);
		
		return null;
	}

}
