package com.ssafy.happyhouse.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeRepo {
	List<Notice> getNotice() throws SQLException; //공지사항 목록
	Notice getNoticeDetail(int notice_id) throws SQLException; //공지사항 상세보기
	int insertNotice(Notice notice) throws SQLException; //공지사항 추가
	int updateNotice(Notice notice) throws SQLException; //공지사항 수정
	int deleteNotice(int notice_id) throws SQLException; //공지사항 삭제
	List<Notice> searchNotice(Map<String, String> map) throws SQLException; // 제목, 내용 검색
	
}
