package com.example.trial.Service;

import org.json.simple.parser.ParseException;

import com.example.trial.Domain.DialogflowVO;

public interface DialogflowService {
	
	public String parsing(String j_response) throws ParseException;
	public DialogflowVO ex_parameters(String j_response) throws ParseException;
}
