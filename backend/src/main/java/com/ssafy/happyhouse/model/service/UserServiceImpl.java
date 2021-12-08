package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repo;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return repo.insertUser(user);
	}

	@Override
	public User getUser(String user_id) {
		return repo.getUser(user_id);
	}

	@Override
	public int deleteUser(String user_id) {
		// TODO Auto-generated method stub
		return repo.deleteUser(user_id);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return repo.updateUser(user);
	}

	@Override
	public User login(User user) throws Exception {
		if(user.getUser_id() == null || user.getUser_pass() == null)
			return null;
		System.out.println("확인"+repo.login(user));
		return repo.login(user);
	}

	@Override
	public User userInfo(String user_id) throws Exception {
		return repo.userInfo(user_id);
	}

	@Override
	public int SelectIdCheck(String user_id) {
		return repo.SelectIdCheck(user_id);
	}

}
