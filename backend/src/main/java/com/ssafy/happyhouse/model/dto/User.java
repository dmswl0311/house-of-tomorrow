package com.ssafy.happyhouse.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String user_id;
	private String user_name;
	private String user_pass;
	private String user_addr;
	private String user_rec_con;
	private String user_rec_daiso;
	private String user_rec_cafe;
	private String user_admin;
}
