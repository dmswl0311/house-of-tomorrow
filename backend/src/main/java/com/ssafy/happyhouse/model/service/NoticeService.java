package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeService {
	List<Notice> getNotice() throws Exception; //공지사항 목록
	Notice getNoticeDetail(int notice_id) throws Exception; //공지사항 상세보기
	int insertNotice(Notice notice) throws Exception; //공지사항 추가
	int updateNotice(Notice notice) throws Exception; //공지사항 수정
	int deleteNotice(int notice_id) throws Exception; //공지사항 삭제
	List<Notice> searchNotice(Map<String, String> map) throws Exception; // 제목, 내용 검색
}
