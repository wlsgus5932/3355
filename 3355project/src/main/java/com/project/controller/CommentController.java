package com.project.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Comment;
import com.project.mybatisDAO.CommentDao;

@RestController
@RequestMapping("/api/board/comment/")
public class CommentController {
	
	@Autowired
	CommentDao dao;
	
	@GetMapping("add")
	public void addComment(@RequestParam("c") String content,
			@RequestParam("uId") String uId, @RequestParam("bId") int bId){
		dao.addComment(content, uId, bId);
	}
	
	@GetMapping("list")
	public List<Comment> getCommentList(@RequestParam("bId") int bId){
		System.out.println(bId);
		System.out.println("==");
		List<Comment> list = dao.getCommentList(bId);
		System.out.println("comment"+list.toString());
		
		return list;
	}
	
	@GetMapping("count")
	public int getCommentCount(@RequestParam("bId") int bId){
		int commentCount = dao.getCommentCount(bId);
		
		return commentCount;
	}
	
	@GetMapping("delete")
	public void commentDelete(@RequestParam("bId") int bId, @RequestParam("cId") int cId){
		System.out.println("코멘트삭제cId:"+cId);
		dao.commentDelete(bId, cId);
	}
	
	@GetMapping("check")
	public int commentCheck(@RequestParam("bId") int bId, @RequestParam("cId") int cId){
		int check = dao.commentCheck(bId, cId);
		System.out.println("댓글체크쓰이는곳"+check);
		return check;
	}
	
	@GetMapping("update")
	public void updateComment(@RequestParam("c") String content, @RequestParam("cId") int cId) {
		dao.updateComment(content, cId);
		System.out.println("업데이트 cId:"+cId);
	}

}
