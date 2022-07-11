package com.boardMVC.app.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardMVC.action.Action;
import com.boardMVC.action.ActionForward;
import com.boardMVC.app.board.dao.BoardDAO;
import com.boardMVC.app.board.vo.BoardDTO;
import com.boardMVC.app.board.vo.BoardVO;
import com.boardMVC.app.member.dao.MemberDAO;

public class BoardListOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HashMap<String, Integer> boardMap = new HashMap<>();
		ActionForward af = new ActionForward();
		BoardDAO dao = new BoardDAO();
//		BoardDAO bDao = new BoardDAO();
//		MemberDAO mDao = new MemberDAO();

		//전체 게시글 개수
		int total = dao.getTotal();
//		int total = bDao.getTotal();
		//사용자가 요청한 페이지
		String temp = req.getParameter("page");
		//사용자가 요청한 페이지가 null이면 1페이지를,
		//null이 아니면 요청한 페이지를 page에 담아준다.
		int page = temp == null ? 1 : Integer.parseInt(temp);
		//한 페이지에 출력되는 게시글의 개수
		int rowCount = 10;
		//한 화면에 나오는 페이지 번호 수
		int pageSize = 10;
		
		//페이지에서 출력되는 게시글 중 첫번째 게시글의 인덱스
		int startRow = (page - 1) * rowCount;
		
		//화면에 출력되는 페이지 번호 중
		//시작 페이지(1, 11, 21, ....)
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		//끝 페이지(10, 20, 30, ...)
		int endPage = startPage + pageSize - 1;
		//실제 마지막 게시글이 출력되는 마지막 페이지 번호
		int realEndPage = (int)Math.ceil(total / (double)rowCount);
		
		//만약 화면에서의 마지막 페이지가 실제 마지막 페이지보다 크다면,
		//실제 마지막 페이지를 endPage에 담아준다.
		//endPage는 항상 10단위로 끝나기 때문에, 14페이지가 마지막 페이지일 경우
		//14페이지를 endPage에 담아준다. 
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		//DB에서 필요한 데이터를 Map에 담는다.
		boardMap.put("startRow", startRow);
		boardMap.put("rowCount", rowCount);

//		//게시글 전체 정보
//		List<BoardVO> boardVO_list = bDao.selectAll(boardMap);
//		
//		//게시글 전체 정보에서
//		List<String> memberIdList = boardVO_list.stream()
//				//작성자 번호로 변경한 뒤
//				.map(vo -> vo.getMemberNumber())
//				//각 번호를 통해 작성자의 정보를 알아낸 후
//				.map(number -> mDao.getInfo(number))
//				//정보 중 작성자의 아이디를 추출
//				.map(vo -> vo.getMemberId())
//				//리스트로 리턴
//				.collect(Collectors.toList());
//		
//		//게시글 전체 정보 + 작성자 아이디를 담을 List
//		List<BoardDTO> boardDTO_list = new ArrayList<>();
//		
//		//게시글 전체 정보를 DTO에 담아준다.
//		boardVO_list.forEach(vo ->{
//			BoardDTO bDTO = new BoardDTO(vo);
//			boardDTO_list.add(bDTO);
//		});
//		
//		//DTO에 있는 작성자의 아이디 필드에 위에서 구한 작성자의 아이디로 대입
//		for (int i = 0; i < boardDTO_list.size(); i++) {
//			boardDTO_list.get(i).setMemberId(memberIdList.get(i));
//		}
		
		//시작 인덱스와, 개수를 전달하여 게시글 목록을 가져온 뒤 requestScope에 담아준다.
		req.setAttribute("boardList", dao.selectAll(boardMap));
//		req.setAttribute("boardList", boardDTO_list);
		//현재 페이지를 requestScope에 담아준다.
		req.setAttribute("page", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("total", total);
		
		af.setRedirect(false);
		af.setPath("/app/board/boardList.jsp");
		
		return af;
	}
	
}


















