package com.ssafy.happyhouse.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Bookmark;
import com.ssafy.happyhouse.model.service.BookmarkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

//<<<<<<< HEAD
////@CrossOrigin("*")
//=======
//>>>>>>> branch 'master' of https://lab.ssafy.com/dmswl_0311/happyhouse_final.git
@RestController
@Api("찜목록 컨트롤러  API V1")
@RequestMapping("/bookmark")
@Slf4j
public class BookmarkController {
	
	@Autowired
	BookmarkService service;
	
	@ApiOperation(value = "찜목록 리스트", notes = "찜목록을 반환한다.", response = Map.class)
	@GetMapping("/list/{bookmark_user_id}") 
	public ResponseEntity<List<Bookmark>> getBookmarklist(@PathVariable("bookmark_user_id")String bookmark_user_id) throws Exception{
		List<Bookmark> list=service.getBookmark(bookmark_user_id);
		ResponseEntity<List<Bookmark>> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	 
	@ApiOperation(value = "찜목록 상세검색", notes = "찜목록 상세페이지로 이동한다.", response = Map.class)
	@GetMapping("/{bookmark_id}")
	public ResponseEntity<Bookmark>getBookmarkDetail (@PathVariable("bookmark_id") int bookmark_id) throws Exception{
		Bookmark list=service.getBookmarkDetail(bookmark_id);
		ResponseEntity<Bookmark> res=new ResponseEntity(list,HttpStatus.OK);
		return res;
	}
	
	@ApiOperation(value = "찜목록 중복검사", notes = "찜목록이 중복 저장되지 않게한다.", response = Map.class)
	@GetMapping("/{bookmark_apt_lat}/{bookmark_apt_lng}")
	public @ResponseBody int BookmarkCheck(@PathVariable("bookmark_apt_lat") String bookmark_apt_lat,@PathVariable("bookmark_apt_lng") String bookmark_apt_lng) throws Exception {
		int bookmarkCount = service.BookmarkCheck(bookmark_apt_lat,bookmark_apt_lng);
		log.debug("id : {}",bookmarkCount);
		return bookmarkCount;
	}
	
	@ApiOperation(value = "찜목록 삭제", notes = "찜목록을 삭제한다.", response = Map.class)
	@DeleteMapping("/{bookmark_id}")
	public String deleteBookmark(@PathVariable("bookmark_id") int bookmark_id) throws Exception{
		int result=service.deleteBookmark(bookmark_id);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "찜목록 등록", notes = "찜목록을 등록한다.", response = Map.class)
	@PostMapping("/")
	public String insertBookmark(@RequestBody Bookmark bookmark) throws Exception{
		int result=service.insertBookmark(bookmark);
		if(result==1) {
			return "success";
		}else {
			return "fail";
		}
	}
}
