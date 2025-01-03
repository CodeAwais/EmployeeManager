package awais.ca.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String department;
	private Long salary;

}
