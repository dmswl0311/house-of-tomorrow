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

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api("공지사항 컨트롤러  API V1")
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
	
	@Autowired
	NoticeService service;
	
	@ApiOperation(value = "공지사항 리스트", notes = "공지사항 리스트를 반환한다.", response = Map.class)
	@GetMapping("/")
	public ResponseEntity<List<Notice>> getNotice() throws Exception{
		List<Notice> list=service.getNotice();
		ResponseEntity<List<Notice>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	 
	@ApiOperation(value = "공지사항 상세검색", notes = "공지사항 상세페이지로 이동한다.", response = Map.class)
	@GetMapping("/{notice_id}")
	public ResponseEntity<Notice>getNoticeDetail (@PathVariable("notice_id") int notice_id) throws Exception{
		Notice list=service.getNoticeDetail(notice_id);
		ResponseEntity<Notice> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
	@ApiOperation(value = "공지사항 삭제", notes = "공지사항을 삭제한다.", response = Map.class)
	@DeleteMapping("/{notice_id}")
	public String deleteNotice(@PathVariable("notice_id") int notice_id) throws Exception{
		int result=service.deleteNotice(notice_id);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "공지사항 수정", notes = "공지사항을 수정한다.", response = Map.class)
	@PutMapping("/")
	public String updateNotice(@RequestBody Notice notice) throws Exception{
		int result=service.updateNotice(notice);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "공지사항 등록", notes = "공지사항을 등록한다.", response = Map.class)
	@PostMapping("/")
	public String insertNotice(@RequestBody Notice notice) throws Exception{
		log.debug("notice img url {}",notice.getNotice_img_url());
		int result=service.insertNotice(notice);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "제목,내용 검색", notes = "제목/내용 검색 결과리스트를 반환한다.", response = Map.class)
	@GetMapping("/{option}/{query}")
	public ResponseEntity<List<Notice>> searchNotice(@PathVariable("option") String option,@PathVariable("query") String query ) throws Exception{
		System.out.println(option);
		System.out.println(query);
		Map<String, String> map=new HashMap<String, String>();
		map.put("option", option);
		map.put("query", query);
		List<Notice> list=service.searchNotice(map);
		ResponseEntity<List<Notice>> res=new ResponseEntity(list,HttpStatus.OK);
		System.out.println(list);
		return res;
	}
	
	
}
