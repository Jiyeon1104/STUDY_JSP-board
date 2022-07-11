package com.boardMVC.app.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.member.dao.MemberDAO;

public class BoardWrite implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		MemberDAO mDao = new MemberDAO();
		ActionForward af = new ActionForward();
		
		String memberId = mDao.getInfo((Integer)session.getAttribute("memberNumber")).getMemberId();
		
		req.setAttribute("memberId", memberId);
		
		af.setRedirect(false);
		af.setPath("/app/board/boardWrite.jsp");
		
		return af;
	}
}




