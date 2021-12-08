package com.ssafy.happyhouse.model.repo;


import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.User;

public interface UserRepo {
	int insertUser(User user); //회원 가입
	User getUser(String user_id); //회원 정보
	int deleteUser(String user_id); //회원 삭제
	int updateUser(User user); //회원 수정
	public User login(User user); //로그인
	public User userInfo(String user_id); //회원정보 얻기 
	int SelectIdCheck(String user_id); // 아이디 중복 검사
}
