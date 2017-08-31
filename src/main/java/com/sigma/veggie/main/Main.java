package com.sigma.veggie.main;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static com.sigma.veggie.service.JsonHelper.json;

import com.sigma.veggie.controller.VeggieController;
import com.sigma.veggie.service.VeggieService;

public class Main {
	
	public static void main(String[] args) {
		after((request, response) -> {
			response.type("application/json");
		});
		
		VeggieController controller = new VeggieController(new VeggieService());
		post("/veggies", (req, res) -> controller.save(req, res), json());
		get("/veggies", (req, res) -> controller.list(req, res), json());
        get("/veggie/:id", (req, res) -> controller.get(req, res), json());
        put("/veggie/:id", (req, res) -> controller.update(req, res), json());
        delete("/veggie/:id", (req, res) -> controller.delete(req, res), json());
    }
}
