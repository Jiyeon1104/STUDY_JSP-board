package com.boardMVC.app.board.vo;

// 나는 기존 테이블의 컬럼의 변형 또는 이외의 연산이 있을 경우에는
// 클래스이름 뒤에 DTO를 붙이겠다.
public class BoardDTO {
	private int boardNumber;
	private String boardTitle;
	private String boardContent;
	private int memberNumber;
	private String memberId;
	private String boardDate;
	private int readCount;
	
	public BoardDTO() {;}

	public BoardDTO(BoardVO boardVO) {
		super();
		this.memberNumber = boardVO.getMemberNumber();
		this.boardNumber = boardVO.getBoardNumber();
		this.boardTitle = boardVO.getBoardTitle();
		this.boardContent = boardVO.getBoardContent();
		this.boardDate = boardVO.getBoardDate();
		this.readCount = boardVO.getReadCount();
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
}
