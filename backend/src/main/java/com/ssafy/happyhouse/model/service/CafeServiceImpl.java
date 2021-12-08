package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Cafe;
import com.ssafy.happyhouse.model.repo.CafeRepo;

@Service
public class CafeServiceImpl implements CafeService {
	@Autowired
	CafeRepo repo;

	@Override
	public List<Cafe> getCafeList() {
		// TODO Auto-generated method stub
		return repo.getCafeList();
	}

}
