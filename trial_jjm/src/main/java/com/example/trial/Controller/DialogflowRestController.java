package com.example.trial.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.parser.ParseException;

import com.example.trial.Domain.DialogflowVO;
import com.example.trial.Domain.EJR_StoreVO;
import com.example.trial.Domain.GD_StoreVO;
import com.example.trial.Domain.GN_StoreVO;
import com.example.trial.Domain.HD_StoreVO;
import com.example.trial.Domain.MD_StoreVO;
import com.example.trial.Persistence.EJR_StoreDAO;
import com.example.trial.Persistence.GD_StoreDAO;
import com.example.trial.Persistence.GN_StoreDAO;
import com.example.trial.Persistence.HD_StoreDAO;
import com.example.trial.Persistence.MD_StoreDAO;
import com.example.trial.Service.DialogflowService;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.gson.Gson;



@RestController
public class DialogflowRestController {
	
	@Autowired
	private DialogflowService diaService;
	
	@Autowired
	private EJR_StoreDAO ejrDAO;
	
	@Autowired
	private GD_StoreDAO gdDAO;
	
	@Autowired
	private GN_StoreDAO gnDAO;
	
	@Autowired
	private HD_StoreDAO hdDAO;
	
	@Autowired
	private MD_StoreDAO mdDAO;
	
	//private String userText = "";
    private final String LANG_CODE = "ko";
    private final String PROJECT_ID = "   "; //id of dialogflow project name
    private String sessionId;
    private final String credential = "   "; //path of dialogflowkey
    private final String URL = "https://dialogflow.googleapis.com/v2/{session=projects/chatbot-restaurant-cirquv/agent/sessions/" +
            sessionId + "}:detectIntent";
    
    
	//index랑 ajax 통신
	@RequestMapping(value = "/message_input", method = RequestMethod.POST)
	@ResponseBody
	public String receivedAjax(@RequestBody String c_msg, HttpSession session) {
		String user_message = c_msg;
		String give_msg = "";
		sessionId=session.getId();
	    System.out.println("세션 ID : " + sessionId);

		System.out.println("ajax에서 받았어 : " + user_message);
		
		try {
			give_msg = sendDialogflow(user_message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ajax로 보낸다:" + give_msg);
		
		return give_msg;
	}
	
		
	//dialogflow 통신
    public String sendDialogflow(String user_message) throws IOException, ParseException {
    	
    	String re_msg = "";

        SessionsClient sessionsClient = SessionsClient.create();
        SessionName session = SessionName.of(PROJECT_ID, sessionId);

        Builder textInput = TextInput.newBuilder().setText(user_message).setLanguageCode(LANG_CODE);

        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

        Gson gson = new Gson();
        String j_response;
        j_response = gson.toJson(response);
        
        //System.out.println("dialogflow에서 가져왔다: " + j_response);
        
        DialogflowVO diaVO = diaService.ex_parameters(j_response);
        
        String place = diaVO.getPlace();
        if(place.equals("")) {
        	re_msg = "장소를 입력해주세요";
        }
        else {
        	re_msg = selectDB(diaVO); 
        }
        
        //System.out.println("dialogflow에서 추출했어 :" + re_msg);
        return re_msg;               
    }
    
    //MongoDB
    public String selectDB(DialogflowVO diaVO) {
    	
    	//String storeList = "";
    	String place = diaVO.getPlace();
    	
    	System.out.println(diaVO.toString());
    	
    	
    	if(place.equalsIgnoreCase("euljiro")) {
    		if(!diaVO.getFood().equals("")) {
    			List<EJR_StoreVO> ejrVO = ejrDAO.findByCategory(diaVO.getFood());
    			return ejrVO.toString();
    		}else {
    			String type = diaVO.getFood_type();
    			List<EJR_StoreVO> ejrVO = ejrDAO.findByType(type);
    			System.out.println(ejrVO);
    			return ejrVO.toString();
    		}
    	}else if(place.equalsIgnoreCase("myeongdong")) {
    		if(!diaVO.getFood().equals("")) {
    			List<MD_StoreVO> mdVO = mdDAO.findByCategory(diaVO.getFood());
    			return mdVO.toString();
    		}else {
    			List<MD_StoreVO> mdVO = mdDAO.findByType(diaVO.getFood_type());
    			return mdVO.toString();
    		}    		
    	}else if(place.equalsIgnoreCase("kondae")) {
    		if(!diaVO.getFood().equals("")) {
    			List<GD_StoreVO> gdVO = gdDAO.findByCategory(diaVO.getFood());
    			return gdVO.toString();
    		}else {
    			List<GD_StoreVO> gdVO = gdDAO.findByType(diaVO.getFood_type());
    			return gdVO.toString();
    		}
    		
    	}else if(place.equalsIgnoreCase("hongdae")) {
    		if(!diaVO.getFood().equals("")) {
    			List<HD_StoreVO> hdVO = hdDAO.findByCategory(diaVO.getFood());
    			return hdVO.toString();
    		}else {
    			List<HD_StoreVO> hdVO = hdDAO.findByType(diaVO.getFood_type());
    			return hdVO.toString();
    		}
    		
    	}else if(place.equalsIgnoreCase("gangnam")) {
    		if(!diaVO.getFood().equals("")) {
    			List<GN_StoreVO> gnVO = gnDAO.findByCategory(diaVO.getFood());
    			return gnVO.toString();
    		}else {
    			List<GN_StoreVO> gnVO = gnDAO.findByType(diaVO.getFood_type());
    			return gnVO.toString();
    		}
    	}else
    		return "검색결과가 없습니다.";    	
    }	
}
