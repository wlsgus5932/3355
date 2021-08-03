package com.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Board;
import com.project.mybatisDAO.BoardDao;
@Service
public class BoardServiceImp implements BoardService{

		@Autowired
		private BoardDao dao;
		
//		@Override
//		public List<Board> getList(String boardlist, int page) {
//			List<Board> list = dao.getList(boardlist, page);
//			return list;
//		}
//
//		@Override
//		public int getCount(String boardlist) {
//			int page = dao.getCount(boardlist);
//			return page;
//		}

		@Override
		public List<Board> getDetail(int boardid) {
			List<Board> detail = dao.getDetail(boardid);
			return detail;
		}

		@Override
		public List<Board> getBestList(String category) {
			List<Board> list = dao.getBestList(category);
			return list;
		}
}
