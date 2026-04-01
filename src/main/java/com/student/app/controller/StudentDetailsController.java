/**
 * 
 */
package com.student.app.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.student.app.error.ErrorMessageHandler;
import com.student.app.error.ErrorResponse;
import com.student.app.error.ResourceNotFoundException;
import com.student.app.jwt.token.jwt.HeaderTokenValidator;
import com.student.app.model.Student;
import com.student.app.response.Response;
import com.student.app.service.intf.StudentServiceIntf;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
@RestController
@RequestMapping("/api/student")
public class StudentDetailsController {

//	@Autowired
//	@Qualifier("studentServicemysql") //"studentServiceHiber" //"studentServicemysql" //"studentServicecb"
//	StudentServiceIntf studentService; 
//	
//	@Autowired
//	public Response response;
//	
//	@Autowired
//	ErrorMessageHandler errorMessageHandler;

	private static final Logger logger = LoggerFactory.getLogger(StudentDetailsController.class);
	private Response response; // Field is final, ensuring immutability
	private ErrorMessageHandler errorMessageHandler; // Field is final, ensuring immutability
	// @Qualifier("studentServicemysql") //"studentServiceHiber"
	// //"studentServicemysql" //"studentServicecb"
	private StudentServiceIntf studentService;
	private HeaderTokenValidator headerTokenValidator;

	// Constructor for injection (Spring automatically injects ReportDAO here)
	public StudentDetailsController(Response response, ErrorMessageHandler errorMessageHandler,
			@Qualifier("studentServicemysql") StudentServiceIntf studentService,
			HeaderTokenValidator headerTokenValidator) {
		this.response = response;
		this.errorMessageHandler = errorMessageHandler;
		this.studentService = studentService;
		this.headerTokenValidator = headerTokenValidator;
	}

	// to load existing student form details
	@Operation(summary = "Retrieve a book by its ID", description = "Fetches details of a specific book using its unique identifier.")
	@ApiResponse(responseCode = "200", description = "Student found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)))
	@ApiResponse(responseCode = "404", description = "Student not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "400", description = "Wrong Input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
	@GetMapping(value = "/studentDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> loadStudentDetails(
			@PathVariable("id") @NotNull(message = "ID cannot be null") @Min(1) String id,
			@RequestHeader Map<String, String> headers, HttpServletRequest httpServletRequest) throws Exception {
		// String encString = null;
		// response = null;

		try {
			logger.info("..........token userName......... {}", headers);
			response.setErrorResponseList(new ArrayList<>());
			boolean isValid = headerTokenValidator.doHeaderTokenValidator(headers, httpServletRequest);// Code to validate the JWT token
					//if(isValid) {																// from request header
			response = studentService.getStudent(id);
			if (!id.equals("0")) {
				errorMessageHandler.addErrortoList(400);
				// throw new NoHandlerFoundException("NoHandlerFoundException", id, null);
			}
					//}
		} catch (ResourceNotFoundException ex) {
			throw new ResourceNotFoundException("");
		} catch (NoHandlerFoundException ex) {
			throw new NoHandlerFoundException("", id, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
//			if(response.getErrorResponseList()!=null) {
//			response.getErrorResponseList().clear();
//			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
