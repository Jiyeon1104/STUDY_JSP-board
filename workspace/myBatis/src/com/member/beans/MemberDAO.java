package com.member.beans;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.config.MyBatisConfig;

public class MemberDAO {
	SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlsessoinFactory();
	SqlSession sqlSession;
	
	public MemberDAO() {
		//자동 커밋 : true
		sqlSession = sqlSessionFactory.openSession(true);
	}
	
	//중복검사
	public boolean checkId(String memberId) {
		return (Integer)sqlSession.selectOne("Member.checkId", memberId) == 1;
	}
	
	//회원가입
	public void join(MemberVO member) {
		sqlSession.insert("Member.join", member);
	}
	
	//로그인
	public int login(String memberId, String memberPw) {
		// 회원번호를 담을 변수
		int userNum = 0;
		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		
		try {
			//만약 검색 결과가 없다면 null이기 때문에 예외처리
			userNum = sqlSession.selectOne("Member.login", map);
		} catch (Exception e) {;}
		
//		MemberVO member = new MemberVO(); 
//		member.setMemberId(memberId);
//		member.setMemberPw(memberPw);
		
		//검색 결과가 있다면 회원의 번호가,
		//검색 결과가 없다면 0이 리턴된다.
		return userNum;
	}
	
	//정보 조회
	public MemberVO select(int memberNum) {
		return sqlSession.selectOne("Member.select", memberNum);
	}
	
	//전체 회원 조회
	public List<MemberVO> select(){
		return sqlSession.selectList("Member.selectAll");
	}
}











