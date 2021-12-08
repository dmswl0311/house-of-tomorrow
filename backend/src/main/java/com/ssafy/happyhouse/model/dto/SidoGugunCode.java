package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SidoGugunCode {
	private String sidoCode;
	private String sidoName;
	private String gugunCode;
	private String gugunName;
}
