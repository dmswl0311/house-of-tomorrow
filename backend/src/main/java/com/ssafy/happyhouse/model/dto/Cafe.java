package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {
	private int id;
	private String name;
	private String subname;
	private int gugun_code;
	private String gugun_name;
	private String dong_code;
	private String dong_name;
	private String jibun_addr;
	private String doro_name;
	private String doro_addr;
	private String lng;
	private String lat;
}
