package com.sigma.veggie.domain;

public class Veggie {

	private Long id;
	private String name;
	private double price;
	
	public Veggie() {
		
	}
	
	public Veggie(Long id) {
		this.id = id;
	}
	
	public Veggie(Long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
}
