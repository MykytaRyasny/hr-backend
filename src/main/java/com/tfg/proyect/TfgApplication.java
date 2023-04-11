package com.tfg.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TfgApplication {

	// private Logger log = LoggerFactory.getLogger(TfgApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
	}

	/*
	 * @Bean
	 * public CommandLineRunner demo(EmployeeRepository employeeRepository) {
	 * return (args) -> {
	 * employeeRepository.save(new Employee("asd", "Mykyta", "Ryasny", "nekit",
	 * "123", "admin"));
	 * log.info("Employee found");
	 * for (Employee employee : employeeRepository.findAll()) {
	 * log.info(employee.toString());
	 * 
	 * }
	 * };
	 * }
	 */
}
