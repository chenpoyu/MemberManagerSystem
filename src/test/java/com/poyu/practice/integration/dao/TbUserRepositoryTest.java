package com.poyu.practice.integration.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TbUserRepositoryTest {

//	@Autowired
//	private TestEntityManager entityManager;
//
//	@Autowired
//	private EmployeeRepository employeeRepository;
//
//	// write test cases here
//	@Test
//	public void whenFindByName_thenReturnEmployee() {
//		// given
//		Employee alex = new Employee("alex");
//		entityManager.persist(alex);
//		entityManager.flush();
//
//		// when
//		Employee found = employeeRepository.findByName(alex.getName());
//
//		// then
//		assertThat(found.getName()).isEqualTo(alex.getName());
//	}
}