package org.java.lessons.mvc.controller;

import java.util.List;

import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

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
	
	@GetMapping("/pizze/{id}")
	public String show(Model model, @PathVariable int id)
	{
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}
	
	@GetMapping("/create")
	public String pizzaCreate(Model model)
	{
		model.addAttribute("pizza", new Pizza());
		return "pizza-create";
	}
	
	@PostMapping("/create")
	public String pizzaStore(
				@Valid @ModelAttribute Pizza pizza,
				BindingResult bindingResult,
				Model model
			) {
		if(bindingResult.hasErrors())
		{
			System.out.println("Error:");
			
			bindingResult.getAllErrors().forEach(System.out::println);
			return "pizza-create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable int id, Model model)
	{
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return "pizza-update";
	}
	
	@PostMapping("/edit/{id}")
	public String updatePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult br,
			Model model)
	{
		if(br.hasErrors())
		{
			br.getAllErrors().forEach(System.out::println);
			return "pizza-update";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id)
	{
		Pizza pizza = pizzaService.findById(id);
		pizzaService.deletePizza(pizza);
		return "redirect:/";
	}
}
