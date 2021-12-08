package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	private int notice_id;
	private String notice_title;
	private String notice_content;
	private String notice_img_url;
	private String notice_user_id;
	private String notice_date;
	
}
