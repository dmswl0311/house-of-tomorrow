package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api("공지사항 컨트롤러  API V1")
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@ApiOperation(value = "회원 가입", notes = "회원가입 실행", response = Map.class)
	@PostMapping("/")
	public String insertUser(@RequestBody User user) throws Exception{
		System.out.println("넘어온회원 정보 "+user);
		int result=service.insertUser(user);
		if(result==1) {
			return "success";
		}else return "fail";
	} 
	
	@ApiOperation(value = "회원 정보", notes = "회원 정보를 출력함.", response = Map.class)
	@GetMapping("/{user_id}")
	public ResponseEntity<User> getUser(@PathVariable("user_id") String user_id) throws Exception{
		User user=service.getUser(user_id);
		ResponseEntity<User> res=new ResponseEntity(user,HttpStatus.OK);
		System.out.println("회원 정보 "+user);
		return res;
	}
	
	@ApiOperation(value = "회원 삭제 (탈퇴)", notes = "회원을 삭제함. (탈퇴)", response = Map.class)
	@DeleteMapping("/{user_id}")
	public String deleteUser(@PathVariable("user_id") String user_id) throws Exception{
		int result=service.deleteUser(user_id);
		if(result==1) {
			return "success";
		}else return "fail";
	}
	
	@ApiOperation(value = "회원 수정", notes = "회원 정보를 수정함.", response = Map.class)
	@PutMapping("/")
	public String updateUser(@RequestBody User user) throws Exception{
		int result=service.updateUser(user);
		if(result==1) {
			return "success";
		}else return "fail";
	}
	@ApiOperation(value = "중복 아이디 체크 ", notes = "아이디가 중복인지 체크함.", response = Map.class)
	@GetMapping("/idcheck/{user_id}")
	public @ResponseBody int idCheck(@PathVariable("user_id") String user_id) throws Exception {
		int idCount = service.SelectIdCheck(user_id);
		log.debug("id : {}",idCount);
		return idCount;
	}
	
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		logger.info("로그인:{}",user);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(user);
			logger.info("loginUser:{}",loginUser);
			if (loginUser != null) {
				//jwtService에 create 메소드에 userid라는 이름으로 방금로그인한사람의 loginUser.getUserid()를 가지고 access-token이라는 이름
				String token = jwtService.create("user_id", loginUser.getUser_id(), "access-token");// key, data, subject
				logger.info("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{user_id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("user_id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String user_id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		//header에 있었던 access-tokens을 받아서 isUsable에 저장 
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.userInfo(user_id);
				System.out.println("로그인시 userInfo"+user);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				logger.info("status{}",status);
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	
}
