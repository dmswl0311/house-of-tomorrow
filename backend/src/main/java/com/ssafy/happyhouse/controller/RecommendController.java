package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.service.RecommendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("추천  API V1")
@RequestMapping("/recommend")
public class RecommendController {
	@Autowired
	RecommendService service;
	
	@ApiOperation(value = "추천 할 아파트", notes = "추천할 아파트 리스트를 반환해준다.", response = Map.class)
	@GetMapping("/{sidogugun}/{flag}")
	public ResponseEntity<List<HouseInfo>> getNotice(@PathVariable("sidogugun") String sidogugun,@PathVariable("flag") String flag) throws Exception{
		List<HouseInfo> list=service.getRecommendList(sidogugun,flag);
		ResponseEntity<List<HouseInfo>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
}
