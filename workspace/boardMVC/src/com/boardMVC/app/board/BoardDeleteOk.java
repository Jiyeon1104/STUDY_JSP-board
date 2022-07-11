package com.boardMVC.app.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardDAO;
import com.boardMVC.app.board.dao.FilesDAO;
import com.boardMVC.app.board.vo.FilesVO;

public class BoardDeleteOk implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String uploadFolder = "D:\\aigb_0900_hds\\jsp\\workspace\\boardMVC\\WebContent\\upload";
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		BoardDAO bDao = new BoardDAO();
		FilesDAO fDao = new FilesDAO();
		ActionForward af = new ActionForward();
		
		List<FilesVO> files = fDao.select(boardNumber);
		
		fDao.delete(boardNumber);
		bDao.delete(boardNumber);
		
		for (int i = 0; i < files.size(); i++) {
			File f = new File(uploadFolder, files.get(i).getFileName());
			if(f.exists()) {
				f.delete();
			}
		}
		
		af.setRedirect(true);
		af.setPath(req.getContextPath() + "/board/BoardListOk.bo");
		
		return af;
	}
}
