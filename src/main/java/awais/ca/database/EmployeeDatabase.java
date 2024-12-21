package awais.ca.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import awais.ca.beans.Employee;

@Repository
public class EmployeeDatabase {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	// Method to insert an employee into database
	public void insertEmployee(Employee employee) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstName", employee.getFirstName());
		namedParameters.addValue("lastName", employee.getLastName());
		namedParameters.addValue("email", employee.getEmail());
		namedParameters.addValue("role", employee.getRole());
		namedParameters.addValue("department", employee.getDepartment());
		namedParameters.addValue("salary", employee.getSalary());

		String query = "INSERT INTO employee(firstName, lastName, email, role, department, salary) "
				+ "VALUES (:firstName, :lastName, :email, :role, :department, :salary)";
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Employee has been sucessfully inserted into the database");

		}
	}
	
	// Method to get the list of employees from database
	public List<Employee> getEmployeeList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM employee";
		return jdbc.query(query, namedParameters, new
		BeanPropertyRowMapper<Employee>(Employee.class));
		}
	
	//Method to get Employee by id
	public List<Employee> getEmployeeById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM employee WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new
		BeanPropertyRowMapper<Employee>(Employee.class));
		}
	
	// Method to get Employees with the same first name
	public List<Employee> getEmployeeFirstName(String firstName) {
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    String query = "SELECT * FROM employee WHERE firstName LIKE :firstName";
	    namedParameters.addValue("firstName", "%" + firstName + "%");
	    return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
	//Method to delete Employee by id
	public void deleteEmployeeById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM employee WHERE id = :id";
		namedParameters.addValue("id", id);
		if (jdbc.update(query, namedParameters) > 0) {
		System.out.println("Deleted employee " + id + " from the database.");
		}
	}
	
	// Method to update an employee in the database
	public void updateEmployee(Employee updatedEmployee) {

	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("id", updatedEmployee.getId());
	    namedParameters.addValue("firstName", updatedEmployee.getFirstName());
	    namedParameters.addValue("lastName", updatedEmployee.getLastName());
	    namedParameters.addValue("email", updatedEmployee.getEmail());
	    namedParameters.addValue("role", updatedEmployee.getRole());
	    namedParameters.addValue("department", updatedEmployee.getDepartment());
	    namedParameters.addValue("salary", updatedEmployee.getSalary());

	    String query = "UPDATE employee SET firstName = :firstName, lastName = :lastName, email = :email, "
	            + "role = :role, department = :department, salary = :salary WHERE id = :id";
	    int rowsAffected = jdbc.update(query, namedParameters);
	    if (rowsAffected > 0) {
	        System.out.println("Updated Employee with ID " + updatedEmployee.getId() + " in the database.");
	    } else {
	        System.out.println("No employee found");
	    }
	}


}
