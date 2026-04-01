package com.student.app.service.intf;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.student.app.model.Student;
import com.student.app.response.Response;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface StudentServiceIntf {

	public void UpdateStudent(Student student)  throws Exception;
	public void addStudent(Student student)  throws Exception;
	public List<Student> getAllStudents()  throws Exception ;
	public List<Student> findAllByOrderByIdAsc();
	public void deleteStudent(String id)  throws Exception;
	public Response getStudent(String id)  throws Exception;
}
