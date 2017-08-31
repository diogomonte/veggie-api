package com.sigma.veggie.repository;

import java.util.ArrayList;
import java.util.List;

import com.sigma.veggie.domain.Veggie;

public class VeggieRepository {
	
	private static List<Veggie> VEGGIES = new ArrayList<>();
	
	public VeggieRepository() {
		VEGGIES.add(new Veggie(1L, "aaa", new Double(1.5)));
		VEGGIES.add(new Veggie(2L, "bbb", new Double(1.5)));
		VEGGIES.add(new Veggie(3L, "ccc", new Double(1.5)));
		VEGGIES.add(new Veggie(4L, "ddd", new Double(1.5)));
	}
	
	public Veggie save(Veggie veggie) {
		VEGGIES.add(veggie);
		return veggie;
	}
	
	public boolean update(Veggie update) {
		try {
			int index = VEGGIES.indexOf(update);
			Veggie veggie = VEGGIES.get(index);
			veggie.setName(update.getName());
			veggie.setPrice(update.getPrice());
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public boolean delete(Long id) {
		VEGGIES.remove(new Veggie(id));
		return true;
	}
	
	public Veggie get(Long id) {
		try {
			int index = VEGGIES.indexOf(new Veggie(id));
			Veggie veggie = VEGGIES.get(index);
			return veggie;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<Veggie> findAll() {
		return VEGGIES;
	}

}
