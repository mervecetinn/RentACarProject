package com.etiya.rentACarSpring;

import java.util.*;

import com.etiya.rentACarSpring.business.abstracts.MessageService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.etiya.rentACarSpring.core.utilities.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.persistence.EntityNotFoundException;

@SpringBootApplication
@EnableSwagger2
@RestControllerAdvice
public class RentACarSpringApplication {
	@Autowired
	private MessageService messageService;


	public static void main(String[] args) {
		SpringApplication.run(RentACarSpringApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.etiya.rentACarSpring"))                                     
          .build();                                           
    }
	
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		Map<String, String> validationErrors=new HashMap<String, String>();
		
		for(FieldError fieldError:exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> error=new ErrorDataResult<Object>(validationErrors,this.messageService.getMessage(Messages.ValidationErrors));
		return error;

	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleNoSuchElementException(NoSuchElementException exception) {
		ErrorResult error=new ErrorResult(this.messageService.getMessage(Messages.NoSuchElementException));
		return error;
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  ErrorResult handleEntityNotFoundException(EntityNotFoundException exception){
		ErrorResult error=new ErrorResult(this.messageService.getMessage(Messages.EntityNotFoundException));
		return  error;
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  ErrorResult handleEmptyResultDataAccessException(EmptyResultDataAccessException exception){
		ErrorResult error=new ErrorResult(this.messageService.getMessage(Messages.EmptyResultDataAccessException));
		return error;
	}


	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
		return new ErrorResult(this.messageService.getMessage(Messages.HttpMessageNotReadableException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleDataIntegrityViolationException(DataIntegrityViolationException exception){
		return new ErrorResult(this.messageService.getMessage(Messages.DataIntegrityViolationException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception){
		return new ErrorResult(this.messageService.getMessage(Messages.InvalidDataAccessApiUsageException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
		return new  ErrorResult (this.messageService.getMessage(Messages.MethodArgumentTypeMismatchException));
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  ErrorResult handleNullPointerException(NullPointerException exception){
		ErrorResult error=new ErrorResult(this.messageService.getMessage(Messages.NullPointerException));
		return  error;
	}







}
