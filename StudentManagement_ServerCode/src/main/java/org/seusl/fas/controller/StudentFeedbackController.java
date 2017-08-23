package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.model.StudentFeedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
public class StudentFeedbackController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@RequestMapping(value = "/addFeedBacks", method = RequestMethod.POST)
	public ResponseEntity<String> addFeedBack(@RequestParam("subject") String subject,
			@RequestParam("message") String message) {

		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			
			StudentFeedback studentFeedback=new StudentFeedback();
			
			studentFeedback.setSubject(subject);
			studentFeedback.setMessage(message);
			
			/*studentFeedback.setDate1(new Date().toString());*/
			
			java.util.Date utilDate= new java.util.Date();
			java.sql.Date sqlDate= new java.sql.Date(utilDate.getTime());
			
			studentFeedback.setDate(sqlDate);
			

			session.save(studentFeedback);

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Feedback Adeed Successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/getFeedbacks", method = RequestMethod.GET)
	public List<StudentFeedback> getFeedbacks() {
		List<StudentFeedback> list = new ArrayList<StudentFeedback>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();

			list = session.createQuery("from StudentFeedback").list();

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}

		return list;
	}

	@RequestMapping(value = "/deleteFeedbacks", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFeedback(@RequestParam("id") int id) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			session.delete(session.find(StudentFeedback.class, new Integer(id)));

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Feedback Deleted Successfully",HttpStatus.GONE);
	}

}
