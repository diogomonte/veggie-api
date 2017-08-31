package com.sigma.veggie.controller;

import static com.sigma.veggie.service.JsonHelper.toObject;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.JsonSyntaxException;
import com.sigma.veggie.domain.Veggie;
import com.sigma.veggie.service.VeggieResult;
import com.sigma.veggie.service.VeggieService;

import spark.Request;
import spark.Response;

public class VeggieController {
	
	private VeggieService veggieService;
	
	public VeggieController(VeggieService veggieService) {
		this.veggieService = veggieService;
	}
	
	public Object list(Request req, Response res) {
		VeggieResult result = veggieService.list();
		switch (result.getCode()) {
			case VeggieResult.OK:
				res.status(HttpStatus.OK_200);
				return new VeggiesResponse(result.getVeggies());
			default:
				res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return new ErrorResponse("Internal error.");
		}
	}
	
	public Object get(Request req, Response res) {
		VeggieResult result = veggieService.list();
		switch (result.getCode()) {
			case VeggieResult.OK:
				res.status(HttpStatus.OK_200);
				return new VeggiesResponse(result.getVeggies());
			case VeggieResult.BAD_REQUEST:
				res.status(HttpStatus.BAD_REQUEST_400);
				return new ErrorResponse(result.getMessage());
			case VeggieResult.NOT_FOUND:
				res.status(HttpStatus.NOT_FOUND_404);
				return new ErrorResponse(result.getMessage());
			default:
				res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return new ErrorResponse("Internal error.");
		}
	}
	
	public Object update(Request req, Response res) {
		try {
			Long id = Long.valueOf(req.params("id"));
			Veggie veggie = toObject(req.body(), Veggie.class);
			VeggieResult result = veggieService.update(id, veggie);
			switch (result.getCode()) {
				case VeggieResult.OK:
					res.status(HttpStatus.OK_200);
					return new OkResponse("Deleted");
				case VeggieResult.BAD_REQUEST:
					res.status(HttpStatus.BAD_REQUEST_400);
					return new ErrorResponse(result.getMessage());
				case VeggieResult.NOT_FOUND:
					res.status(HttpStatus.NOT_FOUND_404);
					return new ErrorResponse(result.getMessage());
				default:
					res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
					return new ErrorResponse("Internal error.");
			}
		} catch (JsonSyntaxException | NumberFormatException e) {
			res.status(HttpStatus.BAD_REQUEST_400);
			return new ErrorResponse("Bad request");
		}
	}
	
	public Object delete(Request req, Response res) {
		try {
			Long id = Long.valueOf(req.params("id"));
			VeggieResult result = veggieService.delete(id);
			switch (result.getCode()) {
				case VeggieResult.OK:
					res.status(HttpStatus.OK_200);
					return new OkResponse("Deleted");
				case VeggieResult.BAD_REQUEST:
					res.status(HttpStatus.BAD_REQUEST_400);
					return new ErrorResponse(result.getMessage());
				case VeggieResult.NOT_FOUND:
					res.status(HttpStatus.NOT_FOUND_404);
					return new ErrorResponse(result.getMessage());
				default:
					res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
					return new ErrorResponse("Internal error.");
			}
		} catch (NumberFormatException e) {
			res.status(HttpStatus.BAD_REQUEST_400);
			return new ErrorResponse("Bad request");
		}
	}
	
	public Object save(Request req, Response res) {
		// TODO Auto-generated method stub
		return null;
	}

}
