package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Comment;

public interface CommentService {
	List<Comment> getComment(int comment_deal_id) throws Exception; //댓글 목록
	int insertComment(Comment comment) throws Exception; //댓글 추가
	int updateComment(Comment comment) throws Exception; //댓글 수정
	int deleteComment(int comment_id) throws Exception; //댓글 삭제
}
