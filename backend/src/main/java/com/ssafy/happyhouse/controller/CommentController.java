package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("댓글 컨트롤러  API V1")
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService service;
	
	@ApiOperation(value = "댓글 리스트", notes = "댓글 리스트를 반환한다.", response = Map.class)
	@GetMapping("/{comment_deal_id}")
	public ResponseEntity<List<Comment>> getComment(@PathVariable("comment_deal_id")int comment_deal_id) throws Exception{
		List<Comment> list=service.getComment(comment_deal_id);
		System.out.println("댓글 리스트 "+list);
		ResponseEntity<List<Comment>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
//	@ApiOperation(value = "댓글 리스트", notes = "댓글 리스트를 반환한다.", response = Map.class)
//	@GetMapping("/{comment_deal_id}")
//	public List<Comment> getComment(@PathVariable("comment_deal_id") int comment_deal_id) throws Exception{
//		List<Comment> list=service.getComment(comment_deal_id);
//		return list;
//	}
//	
	
	@ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.", response = Map.class)
	@DeleteMapping("/{comment_id}")
	public String deleteComment(@PathVariable("comment_id") int comment_id) throws Exception{
		int result=service.deleteComment(comment_id);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.", response = Map.class)
	@PutMapping("/")
	public String updateNotice(@RequestBody Comment comment) throws Exception{
		int result=service.updateComment(comment);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.", response = Map.class)
	@PostMapping("/")
	public String insertComment(@RequestBody Comment comment) throws Exception{
		int result=service.insertComment(comment);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
}
