package com.project.mybatisDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.ReComment;

@Mapper
public interface ReCommentDao {

	List<ReComment> getReCommentList(int bId);

	void addReComment(String content, String uId, int bId, int cId, String ruId);

	void deleteRecomment(int bId, int rId);

	void updateReComment(String content, int rcId);



}
