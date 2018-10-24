package com.mj.springdemo.mvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.springdemo.mvc.dao.StudentRepository;
import com.mj.springdemo.mvc.model.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentDao;

	@Override
	public List<Student> listStudents() {
		return studentDao.listStudents();
	}

	@Override
	public boolean addOrUpdateStudent(Student student) {
		return studentDao.addOrUpdateStudent(student);
	}

	@Override
	public Student getStudent(long studentId) {
		return studentDao.getStudent(studentId);
	}

	@Override
	public boolean deleteStudent(long studentId) {
		return studentDao.deleteStudent(studentId);
	}

	@Override
	public List<Student> searchByFirstOrLastName(String name) {
		return studentDao.searchByFirstOrLastName(name);
	}

}
