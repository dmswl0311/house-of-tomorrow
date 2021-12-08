package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	private int comment_id;
	private String comment_content;
	private int comment_deal_id;
	private String comment_user_id;
	private String comment_date;
}
