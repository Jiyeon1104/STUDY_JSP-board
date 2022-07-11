package com.boardMVC.app.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardReplyDAO;
import com.boardMVC.app.board.vo.BoardReplyDTO;

public class BoardReplyListOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		BoardReplyDAO rDao = new BoardReplyDAO();
		PrintWriter out = resp.getWriter();
		
		List<BoardReplyDTO> replyList = rDao.selectReplies(boardNumber);
		
		JSONArray replies = new JSONArray();

		for(BoardReplyDTO r : replyList) {
			JSONObject reply = new JSONObject();
			reply.put("replyNumber", r.getReplyNumber());
			reply.put("boardNumber", r.getBoardNumber());
			reply.put("memberNumber", r.getMemberNumber());
			reply.put("memberId", r.getMemberId());
			reply.put("replyContent", r.getReplyContent());
			replies.add(reply);
		}
		
		out.print(replies.toJSONString());
		out.close();
		
		return null;
	}

}
