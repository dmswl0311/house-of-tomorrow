package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Bookmark;
import com.ssafy.happyhouse.model.repo.BookmarkRepo;

@Service
public class BookmarkServiceImpl implements BookmarkService{
	@Autowired
	BookmarkRepo repo;

	@Override
	public List<Bookmark> getBookmark(String bookmark_user_id) throws Exception {
		return repo.getBookmark(bookmark_user_id);
	}

	@Override
	public Bookmark getBookmarkDetail(int bookmark_id) throws Exception {
		return repo.getBookmarkDetail(bookmark_id);
	}

	@Override
	public int insertBookmark(Bookmark bookmark) throws Exception {
		return repo.insertBookmark(bookmark);
	}

//	@Override
//	public int updateBookmark(Bookmark bookmark) throws Exception {
//		return repo.updateBookmark(bookmark);
//	}

	@Override
	public int deleteBookmark(int bookmark_id) throws Exception {
		return repo.deleteBookmark(bookmark_id);
	}
//
	@Override
	public int BookmarkCheck(String bookmark_apt_lat, String bookmark_apt_lng) throws Exception {
		return repo.BookmarkCheck(bookmark_apt_lat, bookmark_apt_lng);
	}

}
