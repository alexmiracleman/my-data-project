package com.alex;

import com.alex.service.DataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Starter {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Starter.class, args);
		DataService dataService = applicationContext.getBean(DataService.class);
		dataService.process();
	}

}
