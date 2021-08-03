package com.project.entity;

public class ReComment {
	private int recomment_id;
	private int recomment_board_id;
	private String recommentContent;
	private String recomment_user_id;
	private int recomment_comment_id;
	private String recomment_date;
	private String recomment_ruser_id;
	
	public ReComment() {
	}

	public ReComment(int recomment_id, int recomment_board_id, String recommentContent, String recomment_user_id,
			int recomment_comment_id, String recomment_date, String recomment_ruser_id) {
		super();
		this.recomment_id = recomment_id;
		this.recomment_board_id = recomment_board_id;
		this.recommentContent = recommentContent;
		this.recomment_user_id = recomment_user_id;
		this.recomment_comment_id = recomment_comment_id;
		this.recomment_date = recomment_date;
		this.recomment_ruser_id = recomment_ruser_id;
	}

	public int getRecomment_id() {
		return recomment_id;
	}

	public void setRecomment_id(int recomment_id) {
		this.recomment_id = recomment_id;
	}

	public int getRecomment_board_id() {
		return recomment_board_id;
	}

	public void setRecomment_board_id(int recomment_board_id) {
		this.recomment_board_id = recomment_board_id;
	}

	public String getRecommentContent() {
		return recommentContent;
	}

	public void setRecommentContent(String recommentContent) {
		this.recommentContent = recommentContent;
	}

	public String getRecomment_user_id() {
		return recomment_user_id;
	}

	public void setRecomment_user_id(String recomment_user_id) {
		this.recomment_user_id = recomment_user_id;
	}

	public int getRecomment_comment_id() {
		return recomment_comment_id;
	}

	public void setRecomment_comment_id(int recomment_comment_id) {
		this.recomment_comment_id = recomment_comment_id;
	}


	public String getRecomment_date() {
		return recomment_date;
	}

	public void setRecomment_date(String recomment_date) {
		this.recomment_date = recomment_date;
	}

	public String getRecomment_ruser_id() {
		return recomment_ruser_id;
	}

	public void setRecomment_ruser_id(String recomment_ruser_id) {
		this.recomment_ruser_id = recomment_ruser_id;
	}
	
	@Override
	public String toString() {
		return "ReComment [recomment_id=" + recomment_id + ", recomment_board_id=" + recomment_board_id
				+ ", recommentContent=" + recommentContent + ", recomment_user_id=" + recomment_user_id
				+ ", recomment_comment_id=" + recomment_comment_id + "]";
	}
	
}
