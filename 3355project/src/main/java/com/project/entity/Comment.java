package com.project.entity;

public class Comment {
	private int comment_id;
	private String content;
	private String date;
	private String comment_user_id;
	private String comment_board_id;
	
	public Comment() {
	}
	
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", content=" + content + ", date=" + date + ", comment_user_id="
				+ comment_user_id + ", comment_board_id=" + comment_board_id + "]";
	}

	public Comment(int comment_id, String content, String date, String comment_user_id, String comment_board_id) {
		super();
		this.comment_id = comment_id;
		this.content = content;
		this.date = date;
		this.comment_user_id = comment_user_id;
		this.comment_board_id = comment_board_id;
	}


	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment_user_id() {
		return comment_user_id;
	}

	public void setComment_user_id(String comment_user_id) {
		this.comment_user_id = comment_user_id;
	}

	public String getComment_board_id() {
		return comment_board_id;
	}

	public void setComment_board_id(String comment_board_id) {
		this.comment_board_id = comment_board_id;
	}
	
	

}
