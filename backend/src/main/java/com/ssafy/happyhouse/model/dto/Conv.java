package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conv {
	private int con_id;
	private String con_name;
	private String con_subname;
	private String con_gugun_code;
	private String con_gugun_name;
	private String con_dong_code;
	private String con_dong_name;
	private String con_jibun_addr;
	private String con_doro_name;
	private String con_doro_addr;
	private String con_lng;
	private String con_lat;
}
