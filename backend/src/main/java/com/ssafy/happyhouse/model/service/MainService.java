package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;

public interface MainService {
	List<SidoGugunCode> getSido() throws Exception; //시도 불러오기
	List<SidoGugunCode> getGugun(String sido) throws Exception; //구군 불러오기
	List<HouseInfo> getDong(String sidogugun) throws Exception; //동 불러오기
	List<HouseInfo> getSelect(String sidogugun); //구군에 따라 서울 아파트 불러오기
}
