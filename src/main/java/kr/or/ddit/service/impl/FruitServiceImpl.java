package kr.or.ddit.service.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.FruitMapper;
import kr.or.ddit.service.FruitService;
import kr.or.ddit.vo.FruitVO;
@Service
public class FruitServiceImpl implements FruitService{
	
	@Autowired
	FruitMapper fruitMapper;
	
	//과일 /채소 목록 -->
	@Override
	public JSONObject selectFruit(String fruitGubun){
		/*
		FruitVO -> 1	사과		5	과일
		FruitVO -> 2	배		3	과일
		FruitVO -> 3	바나나	7	과일
		 */
		List<FruitVO> fruitVOList = this.fruitMapper.selectFruit(fruitGubun);
		
		JSONObject data = new JSONObject();
		//fruitVOList를 json데이터로 변환
		//1. cols배열에 넣기(상품명, 수량)
		/*
		 * "cols":[
			{"id":"","label":"상품명","pattern":"","type":"string"},
			{"id":"","label":"수량","pattern":"","type":"number"}
			],
		 */
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray();
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "수량");
		col2.put("type", "number");
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);
		
		//2. rows 배열에 넣기
		/*
		"rows":[
		{"c":[{"v":"사과"},{"v":5}]},
		{"c":[{"v":"배"},{"v":3}]},
		{"c":[{"v":"바나나"},{"v":7}]}
		]
		 */
		JSONArray body = new JSONArray();
		//fruitVOList =>List<FruitVO>
		for(FruitVO fruitVO : fruitVOList) {
			JSONObject fruitName = new JSONObject();
			fruitName.put("v", fruitVO.getFruitName());
			JSONObject fruitAmount = new JSONObject();
			fruitAmount.put("v", fruitVO.getFruitAmount());
			JSONArray row = new JSONArray();
			row.add(fruitName);
			row.add(fruitAmount);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			
			body.add(cell);
		}
		
		data.put("rows",body);
		
		return data;
		
		
	}

}
