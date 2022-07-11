package com.boardMVC.app.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class BoardUpdateOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BoardVO board = new BoardVO();
		BoardDAO bDao = new BoardDAO();
		List<FilesVO> files = null;
		FilesDAO fDao = new FilesDAO();
		ActionForward af = new ActionForward();
		
		String uploadFolder = "D:\\aigb_0900_hds\\jsp\\workspace\\boardMVC\\WebContent\\upload";
		int fileSize = 1024 * 1024 * 5; //5M
		int boardNumber = 0, page = 0;
		
		//파일을 form으로 전달할 때 MultipartRequest로 받는다.
		MultipartRequest multi = new MultipartRequest(req, uploadFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		page = Integer.parseInt(multi.getParameter("page"));
		boardNumber = Integer.parseInt(multi.getParameter("boardNumber"));
		board.setBoardTitle(multi.getParameter("boardTitle"));
		board.setBoardContent(multi.getParameter("boardContent"));
		board.setBoardNumber(boardNumber);
		
		files = fDao.select(boardNumber);
		
		//하나의 서비스를 구현하기 위한 DML의 집합 : 트랜잭션
		//************************
		bDao.update(board);
		fDao.delete(boardNumber);
		fDao.insert(multi, boardNumber);
		//************************
		
		files.forEach(file -> {
			File f = new File(uploadFolder, file.getFileName());
			if(f.exists()) {f.delete();}
		});
		
		//redirect일 경우 데이터를 전달할 수 있는 방법
		//1. 쿼리스트링으로 전달한다.
		//2. Flash(Spring 때 배운다).
		af.setRedirect(true);
		//상세보기
//		af.setPath(req.getContextPath() + "/board/BoardDetailOk.bo?boardNumber=" + boardNumber);
		
		//목록보기
//		af.setPath(req.getContextPath() + "/board/BoardListOk.bo");
		
		//목록보기(페이지 기억)
		af.setPath(req.getContextPath() + "/board/BoardListOk.bo?page=" + page);
		
		return af;
	}

}








