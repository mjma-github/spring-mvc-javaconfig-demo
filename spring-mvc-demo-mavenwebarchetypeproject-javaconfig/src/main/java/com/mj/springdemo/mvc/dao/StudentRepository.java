package com.mj.springdemo.mvc.dao;

import java.util.List;

import com.mj.springdemo.mvc.model.Student;

public interface StudentRepository {
	List<Student> listStudents();

	Student getStudent(long studentId);

	boolean addOrUpdateStudent(Student student);

	boolean deleteStudent(long studentId);

	List<Student> searchByFirstOrLastName(String name);
}
