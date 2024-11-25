package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
//		System.out.println(student);
	}
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>>getStudents()
	{
		return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
		
	}
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student>getStudent(@PathVariable Long id)
	{
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent())
		{
		     return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student>updateStudent(@PathVariable Long id, @RequestBody Student stu)
	{Optional<Student> student = studentRepository.findById(id);
	if(student.isPresent())
	{
		student.get().setStudentName(stu.getStudentName());
		student.get().setStudentEmail(stu.getStudentEmail());
		student.get().setStudentAddress(stu.getStudentAddress());
	     return new ResponseEntity<>(studentRepository.save(student.get()), HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id)
	{
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent())
		{
			studentRepository.deleteById(id);
		     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
