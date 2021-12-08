package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;
import com.ssafy.happyhouse.model.repo.MainRepo;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	MainRepo repo;
	
	@Override
	public List<SidoGugunCode> getSido() throws Exception {
		// TODO Auto-generated method stub
		return repo.getSido();
	}

	@Override
	public List<SidoGugunCode> getGugun(String sido) throws Exception {
		// TODO Auto-generated method stub
		return repo.getGugun(sido);
	}

	@Override
	public List<HouseInfo> getDong(String sidogugun) throws Exception {
		// TODO Auto-generated method stub
		return repo.getDong(sidogugun);
	}

	@Override
	public List<HouseInfo> getSelect(String sidogugun) {
		// TODO Auto-generated method stub
		return repo.getSelect(sidogugun);
	}

}
