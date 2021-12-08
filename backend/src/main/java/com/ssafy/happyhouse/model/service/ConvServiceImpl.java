package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Conv;
import com.ssafy.happyhouse.model.repo.ConvRepo;

@Service
public class ConvServiceImpl implements ConvService {
	@Autowired
	ConvRepo repo;
	@Override
	public List<Conv> getConvList() {
		// TODO Auto-generated method stub
		return repo.getConvList();
	}

}
