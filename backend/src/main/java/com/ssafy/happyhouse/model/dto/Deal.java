package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
	private int deal_id;
	private String deal_title;
	private String deal_content;
	private String deal_date; 
	private String deal_img_url;
	private int deal_rec;
	private String deal_price;
	private int deal_floor;
	private String deal_apt_name;
	private String deal_addr;
	private String deal_tag1;
	private String deal_tag2;
	private String deal_tag3;
	private String deal_user_id;
	private String deal_size;
}
