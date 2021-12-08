package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;
import com.ssafy.happyhouse.model.service.MainServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("메인화면 컨트롤러  API V1")
public class MainController {
	
	@Autowired
	MainServiceImpl service;
	
	// 셀렉트 박스에서 기본 시도 가져오기
	@ApiOperation(value = "시도", notes = "시도 리스트를 반환한다.", response = Map.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCode>> getSido() throws Exception {
		List<SidoGugunCode> list=service.getSido();
		ResponseEntity<List<SidoGugunCode>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
	// 셀렉트 박스에서 구군 가져오기
	// sido는 gugunCode
	// 서울 11
	@ApiOperation(value = "구군", notes = "구군 리스트를 반환한다.", response = Map.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCode>> getGugun(@RequestParam("sido") String sido) throws Exception {
		List<SidoGugunCode> list=service.getGugun(sido);
		ResponseEntity<List<SidoGugunCode>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
	// 셀렉트 박스에서 동 가져오기
	// sidogugun은 dongCode
	// 서울 종로구 11110
	@ApiOperation(value = "동", notes = "동 리스트를 반환한다.", response = Map.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfo>> getDong(@RequestParam("sidogugun") String sidogugun) throws Exception {
		List<HouseInfo> list=service.getDong(sidogugun);
		ResponseEntity<List<HouseInfo>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
}
