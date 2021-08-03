package com.project.service;

import java.util.List;

import com.project.entity.Board;

public interface BoardService {

//	int getCount(String boardlist);

	List<Board> getDetail(int boardid);

//	List<Board> getList(String boardlist, int page);

	List<Board> getBestList(String category);
}
