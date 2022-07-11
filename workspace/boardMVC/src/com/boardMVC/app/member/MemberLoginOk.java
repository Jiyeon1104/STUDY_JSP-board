package com.boardMVC.app.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.member.dao.MemberDAO;

public class MemberLoginOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HashMap<String, String> loginMap = new HashMap<>();
		MemberDAO dao = new MemberDAO();
		int memberNumber = 0;
		ActionForward af = new ActionForward();
		HttpSession session = req.getSession();

		loginMap.put("memberId", req.getParameter("memberId"));
		loginMap.put("memberPw", req.getParameter("memberPw"));
		
		memberNumber = dao.login(loginMap);
		if(memberNumber == 0) {//로그인 실패
			af.setRedirect(false);
			af.setPath("/member/MemberLogin.me?code=" + memberNumber);
		}else {//로그인 성공
			session.setAttribute("memberNumber", memberNumber);
			af.setRedirect(true);
			af.setPath(req.getContextPath() + "/board/BoardListOk.bo");
		}
		
		return af;
	}

}
