package com.student.app.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.dao.intf.StudentDaoIntf;
import com.student.app.model.Student;

@Repository(value = "studentdaomysqlprocedure")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentmysqlProcedureDAO implements StudentDaoIntf {

	private static final Logger logger = LoggerFactory.getLogger(StudentmysqlProcedureDAO.class);

//	 @Autowired
//	  @Qualifier("mysqlTemplate4")
//	  private NamedParameterJdbcTemplate jdbcTemplate; 
//	 
//	 @Autowired
//	 @Qualifier("mysqlcall4")
//	 private SimpleJdbcCall simpleJdbcCall;
//	 
//	  @Autowired
//	  @Qualifier("mysqlDataSource4")
//	  private DataSource dataSource;
//	  
//		@Autowired
//		Student student;

	// @Qualifier("mysqlTemplate4")
	private NamedParameterJdbcTemplate jdbcTemplate;
	// @Qualifier("mysqlcall4")
	private SimpleJdbcCall simpleJdbcCall;

	private Student student; // Field is final, ensuring immutability

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public StudentmysqlProcedureDAO(@Qualifier("mysqlTemplate4") NamedParameterJdbcTemplate jdbcTemplate,
			@Qualifier("mysqlcall4") SimpleJdbcCall simpleJdbcCall, Student student) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcCall = simpleJdbcCall;
		this.student = student;
	}

	public List<Student> getAllStudents_onlyresultset() throws Exception {
		String sql = "{call findAllStudents(:NAMEIN)}";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("NAMEIN", "MARTHAM", Types.VARCHAR);
		ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql, params, new StudentRowMapper());
		logger.info(".............studentslist......................{}", studentslist);
		return studentslist;
	}

	public ArrayList<Student> getAllStudents() throws Exception {
//		 String sql = "{call findAllStudents(:NAMEIN)}";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("NAMEIN", "MARTHAMbabu", Types.VARCHAR);
//		 ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql,params,new StudentRowMapper());
//		 logger.info(".............studentslist......................"+studentslist);

		simpleJdbcCall.withProcedureName("findAllStudents").withCatalogName("student");
		// SqlParameterSource in = new MapSqlParameterSource().addValue("NAMEIN",
		// "MARTHAM");
		Map<String, Object> out = simpleJdbcCall.execute(params);
		logger.info(".............studentslist......................{}", out);
		logger.info(".............studentslist......................{}", out.get("nameout"));
		logger.info(".............studentslist......................{}", out.get("#result-set-1"));
		logger.info(".............markslist......................{}", out.get("#result-set-2"));

		return (ArrayList<Student>) out.get("#result-set-1");
	}

	public Student getStudent(String id) throws Exception {
		String sql = "call findStudentByID (:id)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		// Student student = (Student) jdbcTemplate.query(sql, new CustomerRowMapper());
		ArrayList<Student> studentslist = (ArrayList<Student>) jdbcTemplate.query(sql, params, new StudentRowMapper());
		// ArrayList<Student> studentslist = (ArrayList<Student>)
		// jdbcTemplate.query(sql, params, new StudentRowMapper());
		student = !studentslist.isEmpty() ? studentslist.get(0) : student;
		logger.info(".............studentstudent......................{}", student);
		// Student student = (Student) jdbcTemplate.queryForObject(sql, params, new
		// StudentRowMapper());
		return student;
	}

	public void addStudent(Student student) throws Exception {
		String sql = "{call addStudent(:name,:age,:phone,:country,:gender,:standard,:address,:acceptForm)}";
		MapSqlParameterSource params = new MapSqlParameterSource();
		// params.addValue("id", student.getId());
		params.addValue("name", student.getName());
		params.addValue("age", student.getAge());
		params.addValue("phone", student.getPhone());
		params.addValue("country", student.getCountry());
		params.addValue("gender", student.getGender());
		params.addValue("standard", student.getStandard());
		params.addValue("address", student.getAddress());
		params.addValue("acceptForm", student.getAcceptForm());
		logger.info(".............addStudentaddStudent......................");
		jdbcTemplate.update(sql, params);
	}

	public void updateStudent(Student student) throws Exception {
		String sql = "{call updateStudent(:id,:name,:age,:phone,:country,:gender,:standard,:address,:acceptForm)}";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", student.getId());
		params.addValue("name", student.getName());
		params.addValue("age", student.getAge());
		params.addValue("phone", student.getPhone());
		params.addValue("country", student.getCountry());
		params.addValue("gender", student.getGender());
		params.addValue("standard", student.getStandard());
		params.addValue("address", student.getAddress());
		params.addValue("acceptForm", student.getAcceptForm());

		int i = jdbcTemplate.update(sql, params);
		logger.info(".............UpdateStudent......................{}", i);
	}

	public void deleteStudent(String id) throws Exception {
		String sql = "{call deleteStudent(:id)}";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		int i = jdbcTemplate.update(sql, params);
		logger.info(".............deleteStudent......................{}", i);
	}

}
