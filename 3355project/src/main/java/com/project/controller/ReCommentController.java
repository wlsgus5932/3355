package com.project.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.ReComment;
import com.project.mybatisDAO.ReCommentDao;

@RestController
@RequestMapping("/api/board/recomment/")
public class ReCommentController {
	
	@Autowired
	ReCommentDao dao;
	
	@GetMapping("add")
	public void addReComment(@RequestParam("c") String content, 
				@RequestParam("uId") String uId, @RequestParam("bId") int bId, 
				@RequestParam("cId") int cId, @RequestParam("ruId") String ruId) {
		dao.addReComment(content, uId, bId, cId, ruId);
	}
	
	@GetMapping("list")
	public List<ReComment> list(@RequestParam("bId") int bId){
		List<ReComment> list = dao.getReCommentList(bId);
		System.out.println(list);
		return list;
	}
	
	@GetMapping("delete")
	public void deleteRecomment(@RequestParam("bId") int bId, @RequestParam("rId") int rId) {
		dao.deleteRecomment(bId,rId);
	}
	
	@GetMapping("update")
	public void updateComment(@RequestParam("c") String content, @RequestParam("rcId") int rcId) {
		dao.updateReComment(content, rcId);
	}
	

}
