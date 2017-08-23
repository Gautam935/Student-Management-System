package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Notification;
import org.seusl.fas.model.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/notification")
public class NotificationController {
	
	private final static Logger logger = Logger.getLogger(StudentContoller.class);
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value = "/saveNotification", method = RequestMethod.POST)
	public ResponseEntity<String> addSubject(@RequestParam("messageType") String messageType,
			@RequestParam("message") String message) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			Notification subjectDetails= new Notification();
			subjectDetails.setMessageType(messageType);
			subjectDetails.setMessage(message);

			session.save(subjectDetails);

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Notification Added Successfully", HttpStatus.CREATED);
	}
	@RequestMapping(value="/allNotification",method = RequestMethod.GET)
	public List<Notification> getNotification() {
		List<Notification> list = new ArrayList<Notification>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Notification").list();
			
			t.commit();
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}
		
		return list;
	}
	
	
	
	/**
	 * Delete Notification By Id
	 * 
	 * @param stuId
	 */

	@RequestMapping(value = "/deleteNotification", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<Integer, String>> deleteNotification(@RequestParam("id") int id) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		try {
			t = session.beginTransaction();

			String hql = "DELETE FROM Notification where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();

			logger.info(result + "Notification Deleted Successfully");
			map.put(result, "Notification Deleted Successfully");
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<HashMap<Integer, String>>(map, HttpStatus.GONE);
	}
}
