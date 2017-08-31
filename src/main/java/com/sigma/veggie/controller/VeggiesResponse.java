package com.sigma.veggie.controller;

import java.util.List;

import com.sigma.veggie.domain.Veggie;

public class VeggiesResponse {
	
	private List<Veggie> veggies;
	
	public VeggiesResponse(List<Veggie> veggies) {
		this.veggies = veggies;
	}

}
