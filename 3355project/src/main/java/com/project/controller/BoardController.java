package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Board;
import com.project.entity.UpdateBoard;
import com.project.mybatisDAO.BoardDao;
import com.project.service.BoardService;


@RestController
@RequestMapping("/api/board/")
public class BoardController {
		
		@Autowired
		private BoardService service;
		@Autowired
		private BoardDao dao;
		
		@RequestMapping("list")
		public List<Board> list(@RequestParam("c") String boardlist, @RequestParam("p") int page,
				@RequestParam("f") String ffield, @RequestParam("s") String ssearch) {
			
			String field = "title";
			if(ffield.equals("undefined")) field = field;
			else field = ffield;
			
			String search = "";
			if(ssearch.equals("undefined")) search = search;
			else search = ssearch;
			
			List<Board> list = dao.getList(boardlist, page, field, search);
			System.out.println("리스트는"+list);
			return list;
		}
		
		@RequestMapping("bestlist")
		public List<Board> bestlist(@RequestParam("cn") String category) {
			List<Board> list = service.getBestList(category);
			System.out.println(list.toString());
			return list;
		}
		
		@RequestMapping("count")
		public int getCount(@RequestParam("c") String category, @RequestParam("f") String ffield, @RequestParam("s") String ssearch) {
			System.out.println("fsdfasdfsdfsdafsa:"+ffield+","+ssearch);
			String field = "title";
			if(ffield.equals("undefined")) field = field;
			else field = ffield;
			
			String search = "";
			if(ssearch.equals("undefined")) search = search;
			else search = ssearch;
			
			int c = dao.getCount(category, field, search);
			int count = (int)Math.ceil((double)c/10);
			
			return count;
		}
		
		@RequestMapping("detail/{boardid}")
		public List<Board> detail(@PathVariable("boardid") int boardid){
			System.out.println("boardid=" + boardid);
			List<Board> detail = service.getDetail(boardid);
			System.out.println("detail=" + detail);
			return detail;
		}
		
		@PostMapping("write")
		public void write(@RequestBody Board board) {
			System.out.println(board);
			
			dao.addWrite(board.getTitle(), board.getContent(), board.getHit(), board.getPop(), board.getCategory(), board.getUser_id());
		}
		
		@GetMapping("dayCount")
		public int dayCount(@RequestParam("c") String boardCount) {
//			System.out.println(boardCount);
//			Map<String, Integer> map = new HashMap<>();
			int count = dao.getDayCount(boardCount);
			System.out.println(boardCount + ":" + count);
			System.out.println("============");
//			map.put(boardCount,count);
			return count;
		}
		
		@GetMapping("addpop")
		public String addPop(@RequestParam("bId") int bId, @RequestParam("uId") String uId) {
			System.out.println("bid:"+bId+","+"uid:"+uId);
			String result = dao.addPop(bId, uId);

			return result;
		}
		
		@GetMapping("popCheck")
		public String popCheck(@RequestParam("bId") int bId, @RequestParam("uId") String uId) {
			String result = dao.popCheck(bId, uId);
			System.out.println(bId);
			System.out.println(uId);
			
			return result;
		}
		
		@GetMapping("deletepop")
		public String deletePop(@RequestParam("bId") int bId, @RequestParam("uId") String uId) {
			String result = dao.deletePop(bId, uId);
			
			return result;
		}
		
		@PostMapping("update")
		public void update(@RequestBody UpdateBoard board) {
			System.out.println(board.getbId());
			System.out.println(board.getC());
			System.out.println(board.getT());
			dao.update(board.getbId(), board.getC(), board.getT(), board.getCon());
		}
		
		@GetMapping("deleteWrite")
		public void deleteWrite(@RequestParam("bId") int bId) {
			System.out.println(bId);
			dao.deleteWrite(bId);
		}
		
		@GetMapping("commentCount")
		public int commentCount(@RequestParam("bId") int bId) {
			System.out.println(bId);
			int count = dao.commentCount(bId);
			return count;
		}
		
		@GetMapping("countPop")
		public int countPop(@RequestParam("bId") int bId) {
			int count = dao.countPop(bId);
			return count;
		}
		

		
	}
