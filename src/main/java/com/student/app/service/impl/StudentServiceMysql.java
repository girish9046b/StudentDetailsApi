package com.student.app.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.dao.intf.StudentDaoIntf;
import com.student.app.error.ErrorMessageHandler;
import com.student.app.model.Student;
import com.student.app.response.Response;
import com.student.app.service.intf.StudentServiceIntf;

@Service("studentServicemysql")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentServiceMysql implements StudentServiceIntf {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceMysql.class);
	
	// private final ErrorResponse errorResponse = new ErrorResponse();

//	@Autowired
//	@Qualifier("studentmysqldao") //  //studentmysqldao //studentdaomysqlprocedure
//	StudentDaoIntf studentdao;
//
//	@Autowired
//	Response response;
//
//	@Autowired
//	Student student;
//	
//	@Autowired
//	ErrorMessageHandler errorMessageHandler;
	
	
	private  Response response; // Field is final, ensuring immutability
	private  Student student; // Field is final, ensuring immutability
	private   ErrorMessageHandler errorMessageHandler; // Field is final, ensuring immutability
	//@Qualifier("studentmysqldao") //studentdaomysqlprocedure
	private StudentDaoIntf studentdao; //check interface and dao this has two references sqlquery and procedures

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public StudentServiceMysql(Response response,Student student,ErrorMessageHandler errorMessageHandler,@Qualifier("studentmysqldao") StudentDaoIntf studentdao) {
		this.response = response;
		this.errorMessageHandler = errorMessageHandler;
		this.student = student;
		this.studentdao = studentdao;
	}
	
	
	


	public void UpdateStudent(Student student) throws Exception {
		studentdao.updateStudent(student);
	}
	public void addStudent(Student student) throws Exception {
		studentdao.addStudent(student);
	}

	public void deleteStudent(String id) throws Exception {
		studentdao.deleteStudent(id);

	}

	public ArrayList<Student> getAllStudents() throws Exception {
		logger.info("getAllStudentsmysqlgetAllStudentsmysqlgetAllStudentsmysql-----222");
		return (ArrayList<Student>) studentdao.getAllStudents();
	}

	public Response getStudent(String id) throws Exception {
		student = studentdao.getStudent(id);
		response.setStudent(student);
		logger.info(".........getId...... {} " , student.getId());
		if (student.getId() == 0) {
			
			errorMessageHandler.addErrortoList(404);
			//throw new ResourceNotFoundException("Item ID " + id + " not found");
		}
		//System.out.println(".........StudentServiceMysql......" + response.getErrorResponseList().getFirst().getErrorMessage());
		
		logger.info(".........getId.22..... {} " , student.getId());
		return response;
	}

	@Override
	public ArrayList<Student> findAllByOrderByIdAsc() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

//	@Override
//	public ArrayList<Student> findAllByOrderByIdAsc() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
