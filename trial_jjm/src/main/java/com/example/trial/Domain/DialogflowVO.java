package com.example.trial.Domain;

import org.springframework.stereotype.Repository;

@Repository
public class DialogflowVO {
	private String food;
	/*
	private String korea;
	private String japan;
	private String china;
	private String west;
	*/
	private String food_type;
	private String place;
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getFood_type() {
		return food_type;
	}
	public void setFood_type(String food_type) {
		this.food_type = food_type;
	}
	
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	
	/*
	public String getKorea() {
		return korea;
	}
	public void setKorea(String korea) {
		this.korea = korea;
	}
	
	public String getJapan() {
		return japan;
	}
	public void setJapan(String japan) {
		this.japan = japan;
	}
	
	public String getChina() {
		return china;
	}
	public void setChina(String china) {
		this.china = china;
	}
	
	public String getWest() {
		return west;
	}
	public void setWest(String west) {
		this.west = west;
	}
	*/
	
	@Override
	public String toString() {
		/*
		return "DialogflowVO [place=" + place + ", food_type=" + food_type + ", korea=" + korea + 
				", japan=" + japan + ", china=" + china + ", west=" + west + "]";
		*/
		return "DialogflowVO [place=" + place + ", food_type=" + food_type + ", food=" + food + "]";
	}
}
