package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Conv;

public interface ConvRepo {
	List<Conv> getConvList(); //편의점 리스트
}
