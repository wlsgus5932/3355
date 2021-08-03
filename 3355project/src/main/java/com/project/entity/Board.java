package com.project.entity;

public class Board {
	private int board_id;
	private int rownum;
	private String title;
	private String content;
	private int hit;
	private int pop;
	private String date;
	private String category;
	private String user_id;
	private int commentCount;
	
	public Board() {}

	public Board(int board_id, int rownum, String title, String content, int hit, int pop, String date, String category, String user_id, int commentCount) {
		super();
		this.board_id = board_id;
		this.rownum = rownum;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.pop = pop;
		this.date = date;
		this.category = category;
		this.user_id = user_id;
		this.commentCount = commentCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}


	@Override
	public String toString() {
		return "Board [board_id=" + board_id + ", rownum=" + rownum + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", pop=" + pop + ", date=" + date + ", category=" + category + ", user_id=" + user_id
				+ ", commentCount=" + commentCount + "]";
	}

	
}
