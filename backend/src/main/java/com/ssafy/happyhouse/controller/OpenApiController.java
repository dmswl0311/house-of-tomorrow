package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api("Open API 컨트롤러  API V1")
@CrossOrigin("*")
@RequestMapping("/api")
@Slf4j
public class OpenApiController {
	
	
	
	// 아파트 검색 (ajax 연결, 리스트 반환)
	// 지역코드 필요 (11110 / 최소 시도,구군 입력 필요)
	@ApiOperation(value = "아파트 검색 결과 리스트", notes = "ajax로 불러온 아파트 검색(지역코드) 결과 리스트를 반환한다.", response = Map.class)
	@GetMapping("/apt")
	public Object getAptList(@RequestParam Map<String,Object> param) {
		RestTemplate rt = new RestTemplate();
		UriComponents comp=UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("openapi.molit.go.kr")
				.port(80)
				.path("OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev")
				.queryParam("ServiceKey",param.get("ServiceKey"))
				.queryParam("pageNo",param.get("pageNo"))
				.queryParam("numOfRows",param.get("numOfRows"))
				.queryParam("LAWD_CD",param.get("LAWD_CD"))
				.queryParam("DEAL_YMD",param.get("DEAL_YMD"))
				.build();
		return rt.getForObject(comp.toUri(), Map.class);
	}
	
	@ApiOperation(value = "대출 검색 결과 리스트", notes = "ajax로 불러온 대출 결과 리스트를 반환한다.", response = Map.class)
	@GetMapping("/loan")
	public Object getLoanList(@RequestParam Map<String,Object> param) {
		RestTemplate rt = new RestTemplate();
		UriComponents comp=UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("apis.data.go.kr")
				.port(80)
				.path("B553701/ProductAttribute/getProductAttribute")
				.queryParam("ServiceKey",param.get("ServiceKey"))
				.queryParam("pageNo",param.get("pageNo"))
				.queryParam("numOfRows",param.get("numOfRows"))
				.queryParam("type",param.get("json"))
				.build();
		return rt.getForObject(comp.toUri(), Map.class);
	}
	
}
