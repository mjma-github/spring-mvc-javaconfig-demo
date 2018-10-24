package com.mj.springdemo.mvc.service;

import java.util.List;

import com.mj.springdemo.mvc.model.Student;


public interface StudentService {
	List<Student> listStudents();

	Student getStudent(long studentId);

	boolean addOrUpdateStudent(Student student);

	boolean deleteStudent(long studentId);

	List<Student> searchByFirstOrLastName(String name);
}
