package org.java.lessons.db.pojo;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column( length = 128)
	@Length (
				min = 3,
				max = 128,
				message = "il nome deve contenere 3-128 caratteri"
			)
	private String name;
	
	@Min(0)
	private double price;
	
	private String image;
	
	@Column(columnDefinition = "text")
	private String ingredients;
	
	public Pizza() {}
	public Pizza(String name, double price, String image, String ingredients)
	{
		setName(name);
		setPrice(price);
		setImage(image);
		setIngredients(ingredients);
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		
		return "[id: " + getId() + "]\n"
				+ "nome: " + getName() + "\n"
				+ "prezzo: " + String.format("%.2d", getPrice()) + "\n"
				+ "ingredienti: " + getIngredients();
	}
}
