package com.project.mybatisDAO;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.Comment;


@Mapper
public interface CommentDao {

	public void addComment(String content, String uId, int bId);

	public List<Comment> getCommentList(int bId);

	public int getCommentCount(int bId);

	public int commentCheck(int bId, int cId);

	public void commentDelete(int bId, int cId);

	public List<Comment> getCommentContent(int cId);

	public void updateComment(String content, int cId);

}
