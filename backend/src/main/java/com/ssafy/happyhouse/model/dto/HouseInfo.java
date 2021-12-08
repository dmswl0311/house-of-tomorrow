package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfo {
	private String aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private int convCnt;
	private int cafevCnt;
	private String cname;
	private String csubname;
	private String clat;
	private String clng;
	private int total;
}
