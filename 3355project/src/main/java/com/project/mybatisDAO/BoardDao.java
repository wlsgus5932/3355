package com.project.mybatisDAO;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.Board;

@Mapper
public interface BoardDao {
//	List<Board> getList(String boardlist, int page);
	List<Board> getBestList(String category);
//	int getCount(int boardlist);
	List<Board> getDetail(int boardid);
	void addWrite(String title, String content, int hit, int pop, String category, String user_id);
	int getDayCount(String boardCount);
	String addPop(int bId, String uId);
	String deletePop(int bId, String uId);
	String popCheck(int bId, String uId);
	void update(int bod, String category, String title, String content);
	void deleteWrite(int bId);
	int commentCount(int bId);
	int countPop(int bId);
	List<Board> getList(String boardlist, int page, String field, String search);
	int getCount(String category, String field, String search);
}
