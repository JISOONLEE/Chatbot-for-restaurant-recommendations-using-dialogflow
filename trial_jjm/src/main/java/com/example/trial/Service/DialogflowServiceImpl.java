package com.example.trial.Service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trial.Domain.DialogflowVO;

@Service
public class DialogflowServiceImpl implements DialogflowService{
	
	@Autowired
	private DialogflowVO dialogflowVO;
	/*
	@Autowired
	private StoreVO storeVO;
	*/
	
	public String ex_fulfillment(String j_response) throws ParseException {
    	JSONParser parser = new JSONParser();
        
        JSONObject obj = (JSONObject)parser.parse(j_response);
        JSONObject qResult = (JSONObject)obj.get("queryResult_");
        String fulfillment = (String)qResult.get("fulfillmentText_");
        //String fulfillment = (String)obj.get("fulfillmentText");
        
        //System.out.println("함수에서 추출했어:" + fulfillment);
        String re_msg = fulfillment;
        return re_msg;
    }
	
	public DialogflowVO ex_parameters(String j_response) throws ParseException {
		//String re_msg = "";	
		
		JSONParser parser = new JSONParser();
        
        JSONObject obj = (JSONObject)parser.parse(j_response);
        JSONObject qResult = (JSONObject)obj.get("queryResult_");
        JSONObject parameters = (JSONObject)qResult.get("parameters_");
        JSONObject field = (JSONObject)parameters.get("fields_");
        JSONObject mapData = (JSONObject)field.get("mapData");
        
        //System.out.println(mapData);
        
        //장소
        JSONObject Place = (JSONObject)mapData.get("Place");
        String place = (String)Place.get("kind_");
        
        //음식 종류
        JSONObject Food_type = (JSONObject)mapData.get("Food_type");
        String food_type = (String)Food_type.get("kind_");
        
        //한국
        JSONObject Korean = (JSONObject)mapData.get("Korean");
        String korea = (String)Korean.get("kind_");
        
        //일본
        JSONObject Japanese = (JSONObject)mapData.get("Japanese");
        String japan = (String)Japanese.get("kind_");
        
        //중국
        JSONObject Chinese = (JSONObject)mapData.get("Chinese");
        String china = (String)Chinese.get("kind_");
        
        //미국
        JSONObject Western = (JSONObject)mapData.get("Western");
        String west = (String)Western.get("kind_");
        
        //System.out.println(qResult);
        //System.out.println(parameters);
        
        dialogflowVO.setPlace(place);
        dialogflowVO.setFood_type(food_type);
        /*
        dialogflowVO.setKorea(korea);
        dialogflowVO.setJapan(japan);
        dialogflowVO.setChina(china);
        dialogflowVO.setWest(west);
        */
        
        //String ex_food = dialogflowVO.getFood();
        //String ex_place = dialogflowVO.getPlace();
        
        /*
        if(ex_food.equals("")) {
        	
        }
        else if(!ex_food.equals("") && ex_place.equals("")) { //이전의 데이터에 장소가 없고 음식이 있으면 > 음식만 입력받았었으면
        	if(korea.equals(ex_food)) {
        		korea = "";
        	}
        	else if(japan.equals(ex_food)) {
        		japan = "";
        	}
        	else if(china.equals(ex_food)) {
        		china = "";
        	}
        	else if(west.equals(ex_food)) {
        		west = "";
        	}
        	else
        		dialogflowVO.setFood(korea+japan+china+west);
        }
        */
        
        dialogflowVO.setFood(korea+japan+china+west);
        
        
        //System.out.println("함수에서 추출했어:" + fulfillment);
        /*
        if(place.equals("")) {
            re_msg = "장소를 입력해주세요.";
         }
         else {
            re_msg = dialogflowVO.toString();
         }
		*/
        
        
        return dialogflowVO;
	}
	

	@Override
	public String parsing(String j_response) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


}
