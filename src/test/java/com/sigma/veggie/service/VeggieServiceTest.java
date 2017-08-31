package com.sigma.veggie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.sigma.veggie.domain.Veggie;
import com.sigma.veggie.repository.VeggieRepository;

@RunWith(MockitoJUnitRunner.class)
public class VeggieServiceTest {
	
	@Mock
	private VeggieRepository veggieRepository;
	@InjectMocks
	private VeggieService veggieService = new VeggieService();
	
	@Test
	public void listVeggiesSuccess() {
		Veggie veggie1 = new Veggie(1L);
		Veggie veggie2 = new Veggie(2L);
		when(veggieRepository.findAll()).thenReturn(Lists.newArrayList(veggie1, veggie2));
		
		VeggieResult result = veggieService.list();
		assertEquals("Result should be OK", VeggieResult.OK, result.getCode());
		assertEquals("Result should contain 2 veggies", 2, result.getVeggies().size());
	}
	
	@Test
	public void getVeggieNullIdBadRequest() {
		VeggieResult result = veggieService.get(null);
		assertEquals("Result should be BAD_REQUEST", VeggieResult.BAD_REQUEST, result.getCode());
		assertEquals("Id is required", result.getMessage());
	}
	
	@Test
	public void getVeggieNotFound() {
		when(veggieRepository.get(any())).thenReturn(null);
		
		VeggieResult result = veggieService.get(1L);
		assertEquals("Result should be NOT_FOUND", VeggieResult.NOT_FOUND, result.getCode());
		assertEquals("Veggie with id 1 not found", result.getMessage());
	}
	
	@Test
	public void getVeggieSuccess() {
		Veggie veggie = new Veggie(1L, "Broccoli", 1.5);
		when(veggieRepository.get(1L)).thenReturn(veggie);
		
		VeggieResult result = veggieService.get(1L);
		assertEquals("Result should be OK", VeggieResult.OK, result.getCode());
		assertNotNull("Expected to return a veggie", result.getVeggie());
		assertEquals("Broccoli", result.getVeggie().getName());
		assertEquals(new Double(1.5), new Double(result.getVeggie().getPrice()));
	}
	
	@Test
	public void deleteVeggieBadRequest() {
		VeggieResult result = veggieService.delete(null);
		assertEquals("Result should be BAD_REQUEST", VeggieResult.BAD_REQUEST, result.getCode());
		assertEquals("Id is required", result.getMessage());
	}
	
	@Test
	public void deleteVeggieNotFound() {
		when(veggieRepository.get(any())).thenReturn(null);
		
		VeggieResult result = veggieService.delete(1L);
		assertEquals("Result should be NOT_FOUND", VeggieResult.NOT_FOUND, result.getCode());
		assertEquals("Veggie with id 1 not found", result.getMessage());
	}
	
	@Test
	public void deleteVeggieErrorDeleting() {
		Veggie veggie = new Veggie(1L, "Broccoli", 1.5);
		when(veggieRepository.get(1L)).thenReturn(veggie);
		when(veggieRepository.delete(any())).thenReturn(false);
		
		VeggieResult result = veggieService.delete(1L);
		assertEquals("Result should be ERROR", VeggieResult.ERROR, result.getCode());
		assertEquals("Error deleting veggie", result.getMessage());
	}
	
	@Test
	public void updateVeggieBadRequest() {
		VeggieResult result = veggieService.update(null, null);
		assertEquals("Result should be BAD_REQUEST", VeggieResult.BAD_REQUEST, result.getCode());
		assertEquals("Id and json values are required", result.getMessage());
	}
	
	@Test
	public void updateVeggieNotFound() {
		when(veggieRepository.get(any())).thenReturn(null);
		
		VeggieResult result = veggieService.update(1L, new Veggie());
		assertEquals("Result should be NOT_FOUND", VeggieResult.NOT_FOUND, result.getCode());
		assertEquals("Veggie with id 1 not found", result.getMessage());
	}
	
	@Test
	public void updateVeggieUpdateError() {
		Veggie veggie = new Veggie(1L, "Broccoli", 2.0);
		Veggie update = new Veggie();
		update.setName("Cabbage");
		update.setPrice(1.5);
		when(veggieRepository.get(any())).thenReturn(veggie);
		when(veggieRepository.update(any())).thenReturn(false);
		
		VeggieResult result = veggieService.update(1L, update);
		assertEquals("Result should be ERROR", VeggieResult.ERROR, result.getCode());
		assertEquals("Error updating veggie", result.getMessage());
	}
	
	@Test
	public void updateVeggieOk() {
		Veggie veggie = new Veggie(1L, "Broccoli", 2.0);
		Veggie update = new Veggie();
		update.setName("Cabbage");
		update.setPrice(1.5);
		when(veggieRepository.get(1L)).thenReturn(veggie);
		when(veggieRepository.update(veggie)).thenReturn(true);
		
		VeggieResult result = veggieService.update(1L, update);
		assertEquals("Result should be OK", VeggieResult.OK, result.getCode());
		assertEquals(new Long(1), result.getVeggie().getId());
		assertEquals("Cabbage", result.getVeggie().getName());
		assertEquals(new Double(1.5), new Double(result.getVeggie().getPrice()));
	}
}
