package com.sigma.veggie.service;

import java.util.List;

import com.sigma.veggie.domain.Veggie;

public class VeggieResult {
	
	public static final int OK = 1;
	public static final int ERROR = 2;
	public static final int BAD_REQUEST = 3;
	public static final int NOT_FOUND = 4;

	private int code;
	private String message;
	private Veggie veggie;
	private List<Veggie> veggies;
	
	public VeggieResult(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public VeggieResult(int code, Veggie veggie) {
		this.code = code;
		this.veggie = veggie;
	}
	
	public VeggieResult(int code, List<Veggie> veggies) {
		this.code = code;
		this.veggies = veggies;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Veggie getVeggie() {
		return veggie;
	}
	
	public List<Veggie> getVeggies() {
		return veggies;
	}
	
}
