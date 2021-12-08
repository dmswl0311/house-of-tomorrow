package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.repo.NoticeRepo;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeRepo repo;

	@Override
	public List<Notice> getNotice() throws Exception {
		// TODO Auto-generated method stub
		return repo.getNotice();
	}

	@Override
	public Notice getNoticeDetail(int notice_id) throws Exception {
		// TODO Auto-generated method stub
		return repo.getNoticeDetail(notice_id);
	}

	@Override
	public int insertNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		return repo.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		return repo.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int notice_id) throws Exception {
		// TODO Auto-generated method stub
		return repo.deleteNotice(notice_id);
	}

	@Override
	public List<Notice> searchNotice(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return repo.searchNotice(map);
	}
}
