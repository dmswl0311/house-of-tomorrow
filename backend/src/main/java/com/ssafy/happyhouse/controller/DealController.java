package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Deal;
import com.ssafy.happyhouse.model.service.DealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("매매게시판 컨트롤러  API V1")
@RequestMapping("/deal")
public class DealController {
	@Autowired
	DealService service;
	
	@ApiOperation(value = "매매게시판 리스트", notes = "매매게시판 리스트를 반환한다.", response = Map.class)
	@GetMapping("/")
	public ResponseEntity<List<Deal>> getDeal() throws Exception{
		List<Deal> list=service.getDeal();
		System.out.println("매매게시판 리스트"+list);
		ResponseEntity<List<Deal>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	 
	@ApiOperation(value = "매매게시판 상세검색", notes = "매매게시판 상세페이지로 이동한다.", response = Map.class)
	@GetMapping("/{deal_id}")
	public ResponseEntity<Deal>getDealDetail (@PathVariable("deal_id") int deal_id) throws Exception{
		Deal list=service.getDealDetail(deal_id);
		ResponseEntity<Deal> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
	@ApiOperation(value = "매매게시글 삭제", notes = "매매게시글을 삭제한다.", response = Map.class)
	@DeleteMapping("/{deal_id}")
	public String deleteDeal(@PathVariable("deal_id") int deal_id) throws Exception{
		int result=service.deleteDeal(deal_id);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "매매게시글 수정", notes = "매매게시글을 수정한다.", response = Map.class)
	@PutMapping("/")
	public String updateNotice(@RequestBody Deal deal) throws Exception{
		int result=service.updateDeal(deal);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "매매게시글 등록", notes = "매매게시글을 등록한다.", response = Map.class)
	@PostMapping("/")
	public String insertDeal(@RequestBody Deal deal) throws Exception{
		int result=service.insertDeal(deal);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "추천수 업데이트", notes = "추천수를 업데이트한다.", response = Map.class)
	@PutMapping("/like/{deal_id}")
	public int updateLike(@PathVariable("deal_id") int deal_id) throws Exception{
		int result= service.updateLike(deal_id);
		if(result==1) {
			Deal list=service.getDealDetail(deal_id);
			return list.getDeal_rec();
		}else {
			return -1;
		}
	}
	
	
	@ApiOperation(value = "제목,내용 검색", notes = "제목/내용 검색 결과리스트를 반환한다.", response = Map.class)
	@GetMapping("/{option}/{query}")
	public ResponseEntity<List<Deal>> searchNotice(@PathVariable("option") String option,@PathVariable("query") String query ) throws Exception{
		System.out.println(option);
		System.out.println(query);
		Map<String, String> map=new HashMap<String, String>();
		map.put("option", option);
		map.put("query", query);
		List<Deal> list=service.searchDeal(map);
		ResponseEntity<List<Deal>> res=new ResponseEntity(list,HttpStatus.OK);
		System.out.println(list);
		return res;
	}
	
	@ApiOperation(value = "태그 검색", notes = "태그 검색 결과리스트를 반환한다.", response = Map.class)
	@GetMapping("/tag/{content}")
	public ResponseEntity<List<Deal>> searchTag(@PathVariable("content") String content) throws Exception{
		System.out.println("sdf"+content);
		Map<String, String> map=new HashMap<String, String>();
		map.put("content", content);
		List<Deal> list=service.searchtagDeal(map);
		ResponseEntity<List<Deal>> res=new ResponseEntity(list,HttpStatus.OK);
		System.out.println(list);
		return res;
	}
}
