//package com.student.app.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.ParameterMode;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.StoredProcedureQuery;
//import jakarta.transaction.Transactional;
//import jakarta.transaction.Transactional.TxType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.student.app.dao.intf.StudentRepositoryHiber;
//import com.student.app.error.ResourceNotFoundException;
//import com.student.app.model.Student;
//import com.student.app.response.Response;
//import com.student.app.service.intf.StudentServiceIntf;
//
//@Service("studentServiceHiber")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//	public class StudentServiceHiber implements StudentServiceIntf{
//	     
//	    @Autowired
//	    StudentRepositoryHiber repository;
//	    
//	    @PersistenceContext
//	    private EntityManager manager;
//	    
//		@Autowired
//		Response response;
//		
//		@Autowired
//		Student student;
//	     
////	    public ArrayList<Student> getAllStudents()throws Exception {
////	    	ArrayList<Student> result = (ArrayList<Student>) repository.findAll();
////	        if(result.size() > 0) {
////	            return result;
////	        } else {
////	            return new ArrayList<Student>();
////	        }
////	    }
//	     
//	    public Response getStudent(String idd) throws Exception {
//	    	long id= Long.parseLong(idd);
//	        Optional<Student> student = repository.findById(id);
//	        
//	         System.out.println("......student.get()....."+id);
//	        if(student.isPresent()) {
//	        	 System.out.println("......student.get()....."+student.get());
//	        	 response.setStudent(student.get());
//	            return response;
//	        } else {
//	        	throw new ResourceNotFoundException("Item ID " + id + " not found");
//	        }
//	    }
//	     
////	    public void addStudent(Student student) throws Exception {
////	    	System.out.println("........addStudentaddStudentaddStudent............"+student.toString());
////	    	student = repository.save(student);
////	        }
//	    
//	    public void UpdateStudent(Student student) throws Exception {
//	    	student = repository.save(student);
//		}
//	     
//	    public void deleteStudent(String idd) throws Exception {
//	    	long id= Long.parseLong(idd);
//	        Optional<Student> student = repository.findById(id);
//	         
//	        if(student.isPresent()) 
//	        {
//	            repository.deleteById(id);
//	        } else {
//	            throw new Exception("No employee record exist for given id");
//	        }
//	    }
//
//	  
//	    @Override
//		public ArrayList<Student> findAllByOrderByIdAsc() {
//	    	ArrayList<Student> students = repository.findAllByOrderByIdAsc();
//	    	System.err.println("findAllByOrderByIdAsc..students.."+students);
//	        return students; 
//		}
//		
//	    
//	    public ArrayList<Student>  getAllStudents() throws Exception {
//	    	ArrayList<Student> students = (ArrayList<Student> )manager.createNamedQuery("getAllStudents", Student.class).getResultList();
//	        return students;
//	    }
//
//	    @Override
//	    @Transactional(value =TxType.REQUIRED , rollbackOn = Exception.class)//// we can automatically roll back a transaction if there is an exception
//	    public void addStudent(Student student) throws Exception {
//	    	boolean success= false;
//	        	
//	            StoredProcedureQuery storedProcedure = manager.createStoredProcedureQuery("addStudent")
//	                    .registerStoredProcedureParameter(0 , String.class , ParameterMode.IN)
//	                    .registerStoredProcedureParameter(1 , String.class, ParameterMode.IN)
//	                    .registerStoredProcedureParameter(2 , String.class, ParameterMode.IN)
//	                    .registerStoredProcedureParameter(3 , String.class, ParameterMode.IN)
//			            .registerStoredProcedureParameter(4 , String.class, ParameterMode.IN)
//		                .registerStoredProcedureParameter(5 , String.class, ParameterMode.IN)
//			            .registerStoredProcedureParameter(6 , String.class, ParameterMode.IN)
//		                .registerStoredProcedureParameter(7 , String.class, ParameterMode.IN);
//	             
//	            storedProcedure .setParameter(0, student.getName())
//	                            .setParameter(1, student.getAge())
//	                            .setParameter(2, student.getPhone())
//	                            .setParameter(3, student.getCountry())
//	                            .setParameter(4, student.getGender())
//	                            .setParameter(5, student.getStandard())
//	                            .setParameter(6, student.getAddress())
//					            .setParameter(7, student.getAcceptForm());
//	             
//	             success=storedProcedure.execute();
//	             System.out.println("................successsuccesssuccess............."+success);
//	             //throw new Exception("roll back");//// we can automatically roll back a transaction if there is an exception
//	             //int i=9/0;//// we can automatically roll back a transaction if there is an exception
//	       
//	    }
//
//		
//	}
