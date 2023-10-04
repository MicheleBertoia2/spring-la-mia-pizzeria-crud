package org.java.lessons;

import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaMiaPizzeria1Application implements CommandLineRunner{
	
	@Autowired
	private PizzaService pServ;
	
	public static void main(String[] args) {
		SpringApplication.run(LaMiaPizzeria1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		
		Pizza pizza1 = new Pizza("margherita", 6.50d, "pomodoro, mozzarella");
		Pizza pizza2 = new Pizza("capricciosa", 8.00d, "pomodoro, mozzarella, prosciutto, carciofi, funghi");
		Pizza pizza3 = new Pizza("diavola", 7.50d, "pomodoro, mozzarella, salamino piccante");
		
		pServ.save(pizza1);
		pServ.save(pizza2);
		pServ.save(pizza3);
	}

}
