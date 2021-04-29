package com.durgasoft.springbootrestfulwebservices43rdVideo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.durgasoft.springbootrestfulwebservices43rdVideo.employee.Employee;
import com.durgasoft.springbootrestfulwebservices43rdVideo.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//GET
	//URI:/employees
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees(){
		System.out.println("retrieveAllEmployees method in  EmployeeController");
		return employeeService.findAllEmployees();
		
	}
//	//GET
//	//URI:/employees/{id}
//	@GetMapping("/employees/{id}")
//	public Employee retrieveEmployee(@PathVariable int id) {
//		System.out.println("retrieveEmployee for given ID:"+id);
//		 Employee oneEmployee = employeeService.getOneEmployee(id);
//		if(oneEmployee == null) {
//			throw new EmployeeNotFoundException("Employee not available for given ID: "+ id);
//		}
//		 return oneEmployee;
//		
//	}
//	
	//Create one employee in dbT
	//POST
	//URI:/employees
	//URL:
	//http://localhost:9090/employees
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		System.out.println("createEmployee method in EmployeeController");
		Employee savedEmployee = employeeService.saveEmployee(employee);
		System.out.println("Saved Emoployee Id:"+savedEmployee.getId());
		return savedEmployee;
	}
		
//		//CREATED
//		//employees/{id}
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
//		return 	ResponseEntity.created(location).build();	
//		
//	}
//	
	//DELETE
	//URI:/employees/{id}
	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable int id) {
		Employee employee = employeeService.deleteOneEmployee(id);
		if(employee == null) {
			throw new EmployeeNotFoundException("Employee not available for given ID: "+ id);
		}
	}
//	
	//PUT
	//URI:/employees
	@PutMapping("/employees")
	public void update(@RequestBody Employee employee) {
		employeeService.UpdateOneEmployee(employee);
	}

}
