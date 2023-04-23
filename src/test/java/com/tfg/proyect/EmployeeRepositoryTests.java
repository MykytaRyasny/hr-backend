package com.tfg.proyect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tfg.proyect.model.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tfg.proyect.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeRepositoryTests {

	// dependecy
	@Autowired
	EmployeeRepository employeeRepository;

	// class uder test / system under test (sut)

	/*
	 * All tests should be
	 * Given - When - Then
	 */

	@Test
	@Rollback(true)
	void testRepositoryLayer() {
		// Given
		employeeRepository.save(new EmployeeEntity("81305842Z", "Mykyta", "Ryasny", "nekit", "123", "admin"));
		// When
		EmployeeEntity emp = employeeRepository.findById("81305842Z").get();
		// Then
		assertEquals("81305842Z", emp.getDni());
	}

}
