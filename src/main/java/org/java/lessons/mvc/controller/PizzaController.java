package org.java.lessons.mvc.controller;

import java.util.List;

import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public String index(Model model)
	{
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		return "pizza-index";
	}
	
	public String show(Model model, int id)
	{
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}
}
