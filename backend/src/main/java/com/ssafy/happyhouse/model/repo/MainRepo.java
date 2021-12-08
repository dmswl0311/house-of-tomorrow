package com.ssafy.happyhouse.model.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;

public interface MainRepo {
	List<SidoGugunCode> getSido() throws SQLException; //시도 불러오기
	List<SidoGugunCode> getGugun(String sido) throws SQLException; //구군 불러오기
	List<HouseInfo> getDong(String sidogugun) throws SQLException; //동 불러오기
	List<HouseInfo> getSelect(String sidogugun); //전체 서울 아파트 불러오기
}
