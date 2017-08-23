package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.model.StudentTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task")
public class StudentTaskController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@RequestMapping(value = "/addTasks", method = RequestMethod.POST)
	public ResponseEntity<String> addTask(@RequestBody StudentTask task) {
		task.setMessage(task.getMessage());
		task.setMessageType(task.getMessageType());

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			session.save(task);

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Task Added Successfully",HttpStatus.CREATED);
	}

	@RequestMapping(value = "/studentTasks", method = RequestMethod.GET)
	public List<StudentTask> getTasks() {
		List<StudentTask> tasks = new ArrayList<StudentTask>();
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			tasks = session.createQuery("from StudentTask").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tasks;
	}

}
