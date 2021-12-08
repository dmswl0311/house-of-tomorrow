package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Bookmark;

public interface BookmarkService {
	List<Bookmark> getBookmark(String bookmark_user_id) throws Exception; //찜 목록
	Bookmark getBookmarkDetail(int bookmark_id) throws Exception; //찜목록 상세보기
	int insertBookmark(Bookmark bookmark) throws Exception; //찜목록 추가
//	int updateBookmark(Bookmark bookmark) throws Exception; //찜목록 수정
	int deleteBookmark(int bookmark_id) throws Exception; //찜목록 삭제
	int BookmarkCheck(String bookmark_apt_lat,String bookmark_apt_lng) throws Exception; // 찜목록 중복검사 
}
