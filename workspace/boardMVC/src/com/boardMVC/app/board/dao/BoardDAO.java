package com.boardMVC.app.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.boardMVC.app.board.vo.BoardDTO;
import com.boardMVC.app.board.vo.BoardVO;
import com.boardMVC.mybatis.config.MyBatisConfig;

public class BoardDAO {
	SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlsessoinFactory();
	SqlSession sqlSession;
	
	public BoardDAO() {
		sqlSession = sqlSessionFactory.openSession(true);
	}
	
	//게시글 목록
//	public List<BoardVO> selectAll(Map<String, Integer> boardMap) {
//		return sqlSession.selectList("Board.selectAll", boardMap);
//	}
	
	//게시글 목록
	public List<BoardDTO> selectAll(Map<String, Integer> boardMap) {
		return sqlSession.selectList("Board.selectAll", boardMap);
	}
	
	//게시글 전체 개수
	public int getTotal() {
		return sqlSession.selectOne("Board.getTotal");
	}
	
	//게시글 정보 조회
	public BoardDTO selectDetail(int boardNumber) {
		return sqlSession.selectOne("Board.selectDetail", boardNumber);
	}
	
	//게시글 조회수 증가
	public void updateReadCount(int boardNumber) {
		sqlSession.update("Board.updateReadCount", boardNumber);
	}
	
	//최근 게시글 번호
	public int getSeq() {
		return sqlSession.selectOne("Board.getSeq");
	}
	
	//게시글 추가
	public void insert(BoardVO board) {
		sqlSession.insert("Board.insert", board);
	}
	
	//게시글 삭제
	public void delete(int boardNumber) {
		sqlSession.delete("Board.delete", boardNumber);
	}
	
	//게시글 수정
	public void update(BoardVO board) {
		sqlSession.update("Board.update", board);
	}
	
}












