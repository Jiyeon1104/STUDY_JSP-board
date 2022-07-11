package com.boardMVC.app.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardReplyDAO;

public class BoardReplyDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BoardReplyDAO dao = new BoardReplyDAO();
		
		dao.deleteReply(Integer.parseInt(req.getParameter("replyNumber")));
		
		return null;
	}

}
