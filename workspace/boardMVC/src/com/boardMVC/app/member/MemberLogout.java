package com.boardMVC.app.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;

public class MemberLogout implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		ActionForward af = new ActionForward();
		
		session.invalidate();
		
		af.setRedirect(true);
		af.setPath(req.getContextPath() + "/member/MemberLogin.me");
		
		return af;
	}
}
