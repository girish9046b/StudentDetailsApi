package com.student.app.dao.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.dao.intf.StudentDaoIntf;
import com.student.app.model.Student;

@Repository(value = "studentmysqldao")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentmysqlDAO implements StudentDaoIntf {

	private static final Logger logger = LoggerFactory.getLogger(StudentmysqlDAO.class);

//	@Autowired
//	@Qualifier("mysqlTemplate4")
//	private NamedParameterJdbcTemplate jdbcTemplate;
//
//	// @Autowired
//	// @Qualifier("mysqlcall4")
//	// private SimpleJdbcCall simpleJdbcCall;
//
//	@Autowired
//	@Qualifier("mysqlDataSource4")
//	private DataSource dataSource;
//
//	@Autowired
//	Student student;

	// @Qualifier("mysqlTemplate4")
	private NamedParameterJdbcTemplate jdbcTemplate;
//	@Qualifier("mysqlDataSource4")
//	private DataSource dataSource;
	private Student student; // Field is final, ensuring immutability

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public StudentmysqlDAO(@Qualifier("mysqlTemplate4") NamedParameterJdbcTemplate jdbcTemplate, Student student) {
		this.jdbcTemplate = jdbcTemplate;
		this.student = student;
	}

//	  @Autowired
//	  @Qualifier("mysqlTemplate3")
//	  private NamedParameterJdbcTemplate jdbcTemplate;
//	 
//	  @Autowired
//	  @Qualifier("mysqlDataSource3")
//	  private DataSource dataSource;

	@SuppressWarnings("unchecked")
	public ArrayList<Student> getAllStudents() throws Exception {
		String sql = "SELECT * FROM student";
		return (ArrayList<Student>) jdbcTemplate.query(sql, new StudentRowMapper());
//	        try {
//	    		Connection con = dataSource.getConnection();
//	    		logger.info(".............1111...conconconconconconconcon........"+con);
//	    		con.close();
//	    		} catch (Exception e) {
//	    			// TODO Auto-generated catch block
//	    			e.printStackTrace();
//	    		}

	}

	@SuppressWarnings("unchecked")
	public Student getStudent(String id) throws Exception {
		String sql = "SELECT * FROM student where id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		// Student student = (Student) jdbcTemplate.query(sql, new CustomerRowMapper());

		ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql, params, new StudentRowMapper());
		student = !studentslist.isEmpty() ? studentslist.get(0) : student;
		logger.info("{} ///////////222//////// {}", jdbcTemplate, student);
		return student;
	}

	public void addStudent(Student student) throws Exception {

		String sql = "INSERT INTO student (name, phone, age,gender, country,standard,address,acceptForm)"
				+ " VALUES (:name, :phone, :age , :gender, :country , :standard , :address , :acceptForm)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", student.getName());
		params.addValue("phone", student.getPhone());
		params.addValue("country", student.getCountry());
		params.addValue("standard", student.getStandard());
		params.addValue("age", student.getAge());
		params.addValue("gender", student.getGender());
		params.addValue("address", student.getAddress());
		params.addValue("acceptForm", student.getAcceptForm());

		jdbcTemplate.update(sql, params);
	}

	public void updateStudent(Student student) throws Exception {
		String sql = "UPDATE student SET name=:name , phone=:phone , age=:age , gender=:gender , country=:country , standard=:standard , address=:address ,  acceptForm=:acceptForm WHERE id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", student.getId());
		params.addValue("name", student.getName());
		params.addValue("phone", student.getPhone());
		params.addValue("country", student.getCountry());
		params.addValue("standard", student.getStandard());
		params.addValue("age", student.getAge());
		params.addValue("gender", student.getGender());
		params.addValue("address", student.getAddress());
		params.addValue("acceptForm", student.getAcceptForm());

		jdbcTemplate.update(sql, params);
	}

	public void deleteStudent(String id) throws Exception {
		String sql = "DELETE FROM student WHERE id=:id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbcTemplate.update(sql, params);
	}

}
