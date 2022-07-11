package com.boardMVC.app.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardReplyDAO;
import com.boardMVC.app.board.vo.BoardReplyVO;

public class BoardReplyUpdateOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		BoardReplyVO reply = new BoardReplyVO();
		BoardReplyDAO dao = new BoardReplyDAO();
		
		reply.setReplyNumber(Integer.parseInt(req.getParameter("replyNumber")));
		reply.setReplyContent(req.getParameter("replyContent"));
		
		dao.updateReply(reply);
		
		return null;
	}

}
