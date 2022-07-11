package com.boardMVC.app.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardDAO;
import com.boardMVC.app.board.dao.FilesDAO;
import com.boardMVC.app.board.vo.BoardVO;
import com.boardMVC.app.board.vo.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String uploadFolder = "D:\\aigb_0900_hds\\jsp\\workspace\\boardMVC\\WebContent\\upload";
		int fileSize = 1024 * 1024 * 5;//5M
		BoardDAO bDao = new BoardDAO();
		FilesDAO fDao = new FilesDAO();
		BoardVO board = new BoardVO();
		ActionForward af = new ActionForward();
		
		//요청객체, 업로드폴더 경로, 파일의 크기, 인코딩 방식, 이름변경정책
		MultipartRequest multi = new MultipartRequest(req, uploadFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		board.setBoardTitle(multi.getParameter("boardTitle"));
		board.setBoardContent(multi.getParameter("boardContent"));
		board.setMemberNumber((Integer)req.getSession().getAttribute("memberNumber"));
		
		//게시글 추가
		bDao.insert(board);
		
		//파일 추가
		fDao.insert(multi, bDao.getSeq());
		
		af.setRedirect(true);
		af.setPath(req.getContextPath() + "/board/BoardListOk.bo");
		
		return af;
	}

}
