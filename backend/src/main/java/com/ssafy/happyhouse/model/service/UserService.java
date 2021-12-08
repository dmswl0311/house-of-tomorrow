package com.ssafy.happyhouse.model.service;


import com.ssafy.happyhouse.model.dto.User;

public interface UserService {
	int insertUser(User user); //회원가입
	User getUser(String user_id); //회원정보
	int deleteUser(String user_id); //회원 삭제
	int updateUser(User user); //회원 수정
	public User login(User user) throws Exception; //로그인
	public User userInfo(String user_id) throws Exception; //userinfo
	int SelectIdCheck(String user_id); //아이디 중복검사
}
