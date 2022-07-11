package com.boardMVC.app.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.ActionForward;

public class MemberFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward af = null;
		
		if(command.equals("/member/MemberCheckIdOk.me")) {
			new MemberCheckIdOk().execute(req, resp);
			
		}else if(command.equals("/member/MemberJoin.me")) {
			af = new ActionForward();
			af.setRedirect(false);
			af.setPath("/app/member/join.jsp");
			
		}else if(command.equals("/member/MemberJoinOk.me")) {
			af = new MemberJoinOk().execute(req, resp);
			
		}else if(command.equals("/member/MemberLogin.me")) {
			af = new ActionForward();
			af.setRedirect(false);
			af.setPath("/app/member/login.jsp");
			
		}else if(command.equals("/member/MemberLoginOk.me")) {
			af = new MemberLoginOk().execute(req, resp);
			
		}else if(command.equals("/member/MemberLogout.me")) {
			af = new MemberLogout().execute(req, resp);
		}
		
		//전송안할지
		if(af != null) {
			if(af.isRedirect()) {
				//redirect
				resp.sendRedirect(af.getPath());
			}else {
				//forward
				//request객체에서 Dispatch을 가져온 뒤, 이동할 경로를 전달한다.
				//dispatch : 요청과 응답을 관리해주는 객체
				RequestDispatcher dispatcher = req.getRequestDispatcher(af.getPath());
				//dispatcher객체에서 forward()를 사용할 때 request와 response객체 둘 다 전달해주면
				//응답할 화면까지 데이터가 유지된다.
				dispatcher.forward(req, resp);
			}
		}
	}
}
















