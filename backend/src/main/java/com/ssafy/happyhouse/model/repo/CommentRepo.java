package com.ssafy.happyhouse.model.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Comment;

public interface CommentRepo {
	List<Comment> getComment(int comment_deal_id) throws SQLException; //댓글 목록
	int insertComment(Comment comment) throws SQLException; //댓글 추가
	int updateComment(Comment comment) throws SQLException; //댓글 수정
	int deleteComment(int comment_id) throws SQLException; //댓글 삭제
}
