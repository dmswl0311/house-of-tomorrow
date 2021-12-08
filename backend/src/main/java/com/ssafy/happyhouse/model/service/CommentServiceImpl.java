package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.repo.CommentRepo;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepo repo;
	

	@Override
	public List<Comment> getComment(int comment_deal_id) throws Exception {
		return repo.getComment(comment_deal_id);
	}

	@Override
	public int insertComment(Comment comment) throws Exception {
		return repo.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) throws Exception {
		return repo.updateComment(comment);
	}

	@Override
	public int deleteComment(int comment_id) throws Exception {
		return repo.deleteComment(comment_id);
	}

	
	
}
