package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Cafe;
import com.ssafy.happyhouse.model.dto.Conv;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.repo.RecommendRepo;


@Service
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	RecommendRepo repo;
	
	@Autowired
	MainService sevice;
	
	@Autowired
	ConvService convsevice;

	@Autowired
	CafeService cafesevice;
	
	// 아파트 반경 1km 내에 편의점 개수 구하기
	@Override
	public List<HouseInfo> getRecommendList(String sidogugun,String flag) {
        // 킬로미터(Kilo Meter) 단위
		int ccnt=0;
		int cafecnt=0;
		
		List<HouseInfo> list=sevice.getSelect(sidogugun);
		List<HouseInfo> result=new ArrayList<HouseInfo>();
		
		// 편의점만 선택
		if(flag.equals("1")) {
			List<Conv> convList=convsevice.getConvList();
			// 두 좌표 사이의 거리를 구해 1km 안에 들어가는지 확인
			for (int i = 0; i < list.size(); i++) {
				ccnt=0;
				HouseInfo apt=list.get(i);
				double lat = Double.parseDouble(apt.getLat());
				double lng = Double.parseDouble(apt.getLng());
				double minclat=0;
				double minclng=0;
				double min=Double.MAX_VALUE;
				// 편의점 좌표
				for (int j = 0; j < convList.size(); j++) {
					Conv conv=convList.get(j);
					if(conv.getCon_gugun_code().equals(sidogugun)) {
						double clat=Double.parseDouble(conv.getCon_lat());
						double clng=Double.parseDouble(conv.getCon_lng());
						
						double theta=lng-clng;
						double dist = Math.sin((lat * Math.PI / 180.0)) * Math.sin((clat * Math.PI / 180.0)) + Math.cos((lat * Math.PI / 180.0)) * Math.cos((clat * Math.PI / 180.0)) * Math.cos((theta * Math.PI / 180.0));
						
						dist = Math.acos(dist);
				        dist = (dist * 180 / Math.PI);
				        dist = dist * 60 * 1.1515;
				        
				       dist = dist * 1.609344;
				       
				       if(dist<=1) {
				    	   // 반경 1키로 내에 있으면 cnt++
				    	   ccnt++;
				    	   //System.out.println("반경 1키로 내에 있음 : "+apt.getAptName());
				    	   // 가장 가까운 편의점 위치 구함 
				    	   if(min>dist) {
				    		   min=dist;
				    		   minclat=clat;
				    		   minclng=clng;
				    	   }
				       }
					}
				}
				apt.setTotal(ccnt+cafecnt);
				apt.setClat(String.valueOf(minclat));
		    	apt.setClng(String.valueOf(minclng));
				result.add(apt);
			}
			
		}// 카페만 선택
		else if(flag.equals("2")) {
			List<Cafe> cafeList=cafesevice.getCafeList();
			// 두 좌표 사이의 거리를 구해 1km 안에 들어가는지 확인
			for (int i = 0; i < list.size(); i++) {
				cafecnt=0;
				HouseInfo apt=list.get(i);
				double lat = Double.parseDouble(apt.getLat());
				double lng = Double.parseDouble(apt.getLng());
				double minclat=0;
				double minclng=0;
				double min=Double.MAX_VALUE;
				// 편의점 좌표
				for (int j = 0; j < cafeList.size(); j++) {
					Cafe conv=cafeList.get(j);
					if(conv.getGugun_code()==Integer.parseInt(sidogugun)) {
						double clat=Double.parseDouble(conv.getLat());
						double clng=Double.parseDouble(conv.getLng());
						
						double theta=lng-clng;
						double dist = Math.sin((lat * Math.PI / 180.0)) * Math.sin((clat * Math.PI / 180.0)) + Math.cos((lat * Math.PI / 180.0)) * Math.cos((clat * Math.PI / 180.0)) * Math.cos((theta * Math.PI / 180.0));
						
						dist = Math.acos(dist);
				        dist = (dist * 180 / Math.PI);
				        dist = dist * 60 * 1.1515;
				        
				       dist = dist * 1.609344;
				       
				       if(dist<=1) {
				    	   // 반경 1키로 내에 있으면 cnt++
				    	   cafecnt++;
				    	   //System.out.println("반경 1키로 내에 있음 : "+apt.getAptName());
				    	   // 가장 가까운 편의점 위치 구함 
				    	   if(min>dist) {
				    		   min=dist;
				    		   minclat=clat;
				    		   minclng=clng;
				    	   }
				       }
					}
				}
				apt.setTotal(ccnt+cafecnt);
				apt.setClat(String.valueOf(minclat));
		    	apt.setClng(String.valueOf(minclng));
				result.add(apt);
			}
		}// 둘다 선택
		else if(flag.equals("3")) {
			List<Conv> convList=convsevice.getConvList();
			List<Cafe> cafeList=cafesevice.getCafeList();
			// 두 좌표 사이의 거리를 구해 1km 안에 들어가는지 확인
			for (int i = 0; i < list.size(); i++) {
				cafecnt=0;
				HouseInfo apt=list.get(i);
				double lat = Double.parseDouble(apt.getLat());
				double lng = Double.parseDouble(apt.getLng());
				double minclat=0;
				double minclng=0;
				double min=Double.MAX_VALUE;
				// 편의점 좌표
				for (int j = 0; j < convList.size(); j++) {
					Conv conv=convList.get(j);
					if(conv.getCon_gugun_code().equals(sidogugun)) {
						double clat=Double.parseDouble(conv.getCon_lat());
						double clng=Double.parseDouble(conv.getCon_lng());
						
						double theta=lng-clng;
						double dist = Math.sin((lat * Math.PI / 180.0)) * Math.sin((clat * Math.PI / 180.0)) + Math.cos((lat * Math.PI / 180.0)) * Math.cos((clat * Math.PI / 180.0)) * Math.cos((theta * Math.PI / 180.0));
						
						dist = Math.acos(dist);
				        dist = (dist * 180 / Math.PI);
				        dist = dist * 60 * 1.1515;
				        
				       dist = dist * 1.609344;
				       
				       if(dist<=1) {
				    	   // 반경 1키로 내에 있으면 cnt++
				    	   ccnt++;
				    	   //System.out.println("반경 1키로 내에 있음 : "+apt.getAptName());
				    	   // 가장 가까운 편의점 위치 구함 
				    	   if(min>dist) {
				    		   min=dist;
				    		   minclat=clat;
				    		   minclng=clng;
				    	   }
				       }
					}
				}
				// 카페 좌표
				for (int j = 0; j < cafeList.size(); j++) {
					Cafe conv=cafeList.get(j);
					if(conv.getGugun_code()==Integer.parseInt(sidogugun)) {
						double clat=Double.parseDouble(conv.getLat());
						double clng=Double.parseDouble(conv.getLng());
						
						double theta=lng-clng;
						double dist = Math.sin((lat * Math.PI / 180.0)) * Math.sin((clat * Math.PI / 180.0)) + Math.cos((lat * Math.PI / 180.0)) * Math.cos((clat * Math.PI / 180.0)) * Math.cos((theta * Math.PI / 180.0));
						
						dist = Math.acos(dist);
				        dist = (dist * 180 / Math.PI);
				        dist = dist * 60 * 1.1515;
				        
				       dist = dist * 1.609344;
				       
				       if(dist<=1) {
				    	   // 반경 1키로 내에 있으면 cnt++
				    	   cafecnt++;
				    	   //System.out.println("반경 1키로 내에 있음 : "+apt.getAptName());
				    	   // 가장 가까운 편의점 위치 구함 
				    	   if(min>dist) {
				    		   min=dist;
				    		   minclat=clat;
				    		   minclng=clng;
				    	   }
				       }
					}
				}
				apt.setTotal(ccnt+cafecnt);
				apt.setClat(String.valueOf(minclat));
		    	apt.setClng(String.valueOf(minclng));
				result.add(apt);
			}
		}
		// 테스트 데이터
//		List<Conv> convList=new ArrayList<Conv>();
//		convList.add(new Conv(1, "test", "test", 11110, "test", "test", "test", "test", "test", "test", "127.1343601", "37.5382469"));
//		convList.add(new Conv(2, "test", "test", 11110, "test", "test", "test", "test", "test", "test", "127.0648903", "37.50779322"));
//		convList.add(new Conv(3, "test", "test", 11110, "test", "test", "test", "test", "test", "test", "126.9859101", "37.57692755"));
//		convList.add(new Conv(4, "test", "test", 11110, "test", "test", "test", "test", "test", "test", "127.0216405", "37.57427367"));
//		convList.add(new Conv(5, "test", "test", 11110, "test", "test", "test", "test", "test", "test", "127.0216902", "37.5739407"));
		// result totalCnt로 내림차순 정렬
		Collections.sort(result, new Comparator<HouseInfo>() {

			@Override
			public int compare(HouseInfo o1, HouseInfo o2) {
				// TODO Auto-generated method stub
				return o2.getTotal()-o1.getTotal();
			}
		});
		return result;
	}
	
}
