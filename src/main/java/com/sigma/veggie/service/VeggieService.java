package com.sigma.veggie.service;

import java.util.List;

import com.google.common.base.Strings;
import com.sigma.veggie.domain.Veggie;
import com.sigma.veggie.repository.VeggieRepository;

public class VeggieService {
	
	private VeggieRepository veggieRepository;
	
	public VeggieService() {
		this.veggieRepository = new VeggieRepository();
	}
	
	public VeggieResult list() {
		List<Veggie> veggies = veggieRepository.findAll();
		return new VeggieResult(VeggieResult.OK, veggies);
	}
	
	public VeggieResult get(Long id) {
		if (id == null) {
			return new VeggieResult(VeggieResult.BAD_REQUEST, "Id is required");
		}
		Veggie veggie = veggieRepository.get(id);
		if (veggie == null) {
			return new VeggieResult(VeggieResult.NOT_FOUND, String.format("Veggie with id %d not found", id));
		}
		return new VeggieResult(VeggieResult.OK, veggie);
	}
	
	public VeggieResult save(Veggie veggie) {
		if (veggie == null) {
			return new VeggieResult(VeggieResult.BAD_REQUEST, "Name and price are required");
		}
		if (Strings.isNullOrEmpty(veggie.getName())) {
			return new VeggieResult(VeggieResult.BAD_REQUEST, "Name is required");
		}
		Veggie saved = veggieRepository.save(veggie);
		return new VeggieResult(VeggieResult.OK, saved);
	}
	
	public VeggieResult update(Long id, Veggie updateVeggie) {
		if (id == null || updateVeggie == null) {
			return new VeggieResult(VeggieResult.BAD_REQUEST, "Id and json values are required");
		}
		Veggie veggie = veggieRepository.get(id);
		if (veggie == null) {
			return new VeggieResult(VeggieResult.NOT_FOUND, String.format("Veggie with id %d not found", id));
		}
		veggie.setName(updateVeggie.getName());
		veggie.setPrice(updateVeggie.getPrice());
		boolean updated = veggieRepository.update(veggie);
		if (!updated) {
			return new VeggieResult(VeggieResult.ERROR, "Error updating veggie");
		}
		return new VeggieResult(VeggieResult.OK, veggie);
	}
	
	public VeggieResult delete(Long id) {
		if (id == null) {
			return new VeggieResult(VeggieResult.BAD_REQUEST, "Id is required");
		}
		Veggie veggie = veggieRepository.get(id);
		if (veggie == null) {
			return new VeggieResult(VeggieResult.NOT_FOUND, String.format("Veggie with id %d not found", id));
		}
		boolean deleted = veggieRepository.delete(id);
		if (!deleted) {
			return new VeggieResult(VeggieResult.ERROR, "Error deleting veggie");
		}
		return new VeggieResult(VeggieResult.OK, "Deleted");
	}

}
