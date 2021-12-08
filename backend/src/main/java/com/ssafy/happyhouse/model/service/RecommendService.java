package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Cafe;
import com.ssafy.happyhouse.model.dto.Conv;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface RecommendService {
	List<HouseInfo> getRecommendList(String sidogugun,String flag); //편의점,카페 개수 구한 아파트 리스트
}
