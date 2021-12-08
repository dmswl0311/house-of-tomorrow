package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;


import com.ssafy.happyhouse.model.dto.Deal;

public interface DealService {
	List<Deal> getDeal() throws Exception; //매매게시판 목록
	Deal getDealDetail(int deal_id) throws Exception; //매매게시판 상세보기
	int insertDeal(Deal deal) throws Exception; //매매게시판 추가
	int updateDeal(Deal deal) throws Exception; //매매게시판 수정
	int deleteDeal(int deal_id) throws Exception; //매매게시판 삭제
	List<Deal> searchDeal(Map<String, String> map) throws Exception; // 제목, 내용 검색
	int updateLike(int deal_id) throws Exception; //추천수 업데이트 
	List<Deal> searchtagDeal(Map<String, String> map) throws Exception; // 해당하는 태그 검색
}
