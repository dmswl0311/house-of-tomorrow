package com.ssafy.happyhouse.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.ssafy.happyhouse.model.dto.Deal;

public interface DealRepo {
	List<Deal> getDeal() throws SQLException; //매매게시판 목록
	Deal getDealDetail(int deal_id) throws SQLException; //매매게시판 상세보기
	int insertDeal(Deal deal) throws SQLException; //매매게시판 추가
	int updateDeal(Deal deal) throws SQLException; //매매게시판 수정
	int deleteDeal(int deal_id) throws SQLException; //매매게시판 삭제
	int updateLike(int deal_id) throws SQLException; //추천수 업데이트 
	List<Deal> searchDeal(Map<String, String> map) throws SQLException; // 제목, 내용 검색
	List<Deal> searchtagDeal(Map<String, String> map) throws SQLException; // 해당하는 태그 검색
}
