package com.mj.springdemo.mvc.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.springframework.stereotype.Repository;

import com.mj.springdemo.mvc.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Student> listStudents() {
		//Session session = sessionFactory.getCurrentSession();
		//Query<Student> query = session.createQuery("from Student", Student.class);
		//return query.getResultList();
		
//		CriteriaQuery<Student> criteriaQuery = em.getCriteriaBuilder().createQuery(Student.class);
//		criteriaQuery.from(Student.class);
//		return em.createQuery(criteriaQuery).getResultList();
		
		return em.createQuery("from Student order by lastName", Student.class).getResultList();
	}

	@Override
	public boolean addOrUpdateStudent(Student student) {
		try {
			em.merge(student);
		}
		catch(EntityExistsException | IllegalArgumentException | TransactionRequiredException ex) {
			return false;
		}
		
		return true;
	}

	@Override
	public Student getStudent(long studentId) {
		return em.find(Student.class, studentId);
	}

	@Override
	public boolean deleteStudent(long studentId) {
		Query query = em.createQuery("delete from Student where id = :studentId");
		query.setParameter("studentId", studentId);
		int rowsAffected = query.executeUpdate();
		
		if(rowsAffected == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Student> searchByFirstOrLastName(String name) {
		Query query = em.createQuery("from Student where lower(firstName) like :name or lower(lastName) like :name", Student.class);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		return query.getResultList();
	}
}
