package com.amazonaws.samples;

public class TestResponse {

	private String name;
	private String place;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public TestResponse(String name, String place) {
		super();
		this.name = name;
		this.place = place;
	}
}
