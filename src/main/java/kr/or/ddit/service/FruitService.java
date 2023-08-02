package kr.or.ddit.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.or.ddit.vo.FruitVO;

public interface FruitService {

	//과일 채소 목록
	public JSONObject selectFruit(String fruitGubun);

}
