package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Deal;
import com.ssafy.happyhouse.model.repo.DealRepo;

@Service
public class DealServiceImpl implements DealService{
	@Autowired
	DealRepo repo;
	
	@Override 
	public List<Deal> getDeal() throws Exception {
		return repo.getDeal();
	}
	
	@Override
	public Deal getDealDetail(int deal_id) throws Exception {
		return repo.getDealDetail(deal_id);
	}
	
	@Override 
	public int insertDeal(Deal deal) throws Exception {
		return repo.insertDeal(deal);
	}
	
	@Override
	public int updateDeal(Deal deal) throws Exception {
		return repo.updateDeal(deal);
	}
	
	@Override
	public int deleteDeal(int deal_id) throws Exception {
		return repo.deleteDeal(deal_id);
	}
	
	@Override  
	public List<Deal> searchDeal(Map<String, String> map) throws Exception {
		return repo.searchDeal(map);
	}

	@Override
	public int updateLike(int deal_id) throws Exception {
		return repo.updateLike(deal_id);
	}

	@Override
	public List<Deal> searchtagDeal(Map<String, String> map) throws Exception {
		return repo.searchtagDeal(map);
	}


}
