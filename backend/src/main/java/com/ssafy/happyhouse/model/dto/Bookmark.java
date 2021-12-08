package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
	private int bookmark_id; 
	private String bookmark_user_id;
	private String bookmark_apt_name;
	private String bookmark_apt_dongname;
	private int bookmark_apt_floor;
	private String bookmark_apt_price;
	private String bookmark_apt_lat;
	private String bookmark_apt_lng;
	
}
