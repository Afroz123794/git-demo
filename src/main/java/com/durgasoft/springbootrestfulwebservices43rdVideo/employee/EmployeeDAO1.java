package com.durgasoft.springbootrestfulwebservices43rdVideo.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO1 {
	
	private static int count = 11;
	
	private static final String INSERT_STUDENT = "INSERT into STUDENT_DETAILS1(NO,NAME,EMAIL,MOBILE) values(:no, :name, :email, :mobile)";
	private static final String UPDATE_QUERY = "UPDATE STUDENT_DETAILS1 set NAME=:name, EMAIL = :email,MOBILE=:mobile where NO = :no";
	private static final String DELETE_STUDENT = "DELETE FROM STUDENT_DETAILS1 WHERE NO=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//	/**
//	 * get all Employee details
//	 * @return List of Employees
//	 */
	
	
	public List<Employee> findAll()
	{
		String sql="SELECT * FROM STUDENT_DETAILS";
		List<Employee> employeelist = jdbcTemplate.query(sql, new RowMapper<Employee>()
		{

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				System.out.println("Calling map row method,the row num is: "+rowNum);
				Employee employee = new Employee();
				employee.setId(rs.getInt("NO"));
				employee.setName(rs.getString("NAME"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setMobile(rs.getString("MOBILE"));
				return employee;
			}
			
		});
		
		return employeelist;
		
		
	}
	
//	public Employee save(Employee employee) {
//		System.out.println("save method in EmployeeDAO1");
//		if(employee.getId() == null) {
//			employee.setId(++count);
//		}
		
//	 * Persist the Employee Object into db or MYSQL database
//	 * @param employee
//	 * @return
//	 */
		
		public Employee save(Employee employee) 
		{
			System.out.println("save method in EmployeeDAO1");
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("name",employee.getName());
			params.put("no", employee.getId());
			params.put("email", employee.getEmail());
			params.put("mobile", employee.getMobile());
		
			int noOfRowsInserted = namedParameterJdbcTemplate.update(INSERT_STUDENT, params);
			
			System.out.println("Number Of Rows Inserted:"+noOfRowsInserted);
			
			return employee;
			
		}
		
		public void updateEmployee(Employee employee) {
			 // Adding params using MapSqlParameterSource class
			SqlParameterSource parameterSource=new MapSqlParameterSource()
					.addValue("email", employee.getEmail())
					.addValue("no", employee.getId())
					.addValue("mobile", employee.getMobile())
					.addValue("name", employee.getName());
			int noOfRowsUpdated = namedParameterJdbcTemplate.update(UPDATE_QUERY, parameterSource);
			System.out.println("noOfRowsUpdated: "+ noOfRowsUpdated);
		}
					
		public Employee deleteEmployee(int id) {
			int noOfRowsDeleted = jdbcTemplate.update(DELETE_STUDENT, id);
			System.out.println("noOfRowsDeleted: "+noOfRowsDeleted);
			return null;
		}
		
		
		
	
}
	
	


