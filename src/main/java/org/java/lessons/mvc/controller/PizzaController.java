package org.java.lessons.mvc.controller;

import java.util.List;

import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public String index(Model model,
						@RequestParam(required = false) String name
						)
	{
		
		List<Pizza> pizze = name == null
							? pizzaService.findAll()
							: pizzaService.searchByName(name);
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("name", name);
		return "pizza-index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable int id)
	{
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}
}
