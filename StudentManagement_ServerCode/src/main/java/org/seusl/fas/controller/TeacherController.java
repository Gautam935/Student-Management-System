package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gautam Kumar
 *
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

	private static final Logger logger = Logger.getLogger(TeacherController.class);
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	/**
	 * Save a Teacher
	 * 
	 * @param teacherId
	 *            name address telNo email qualification
	 * 
	 */

	@RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
	public ResponseEntity<String> addTeacher(@RequestParam("teacherId") String teacherId,
			@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("telNo") String telNo, @RequestParam("email") String email,
			@RequestParam("qualification") String qualification) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			Teacher teacherInfo=new Teacher();

			teacherInfo.setId(teacherId);
			teacherInfo.setName(name);
			teacherInfo.setAddress(address);
			teacherInfo.setTelNo(telNo);
			teacherInfo.setEmail(email);
			teacherInfo.setQualification(qualification);

			session.save(teacherInfo);
			logger.info("TeacherInfo========" + teacherInfo);
			;

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Record Added Successfully", HttpStatus.CREATED);
	}

	/**
	 * @return Display all teachers
	 */

	@RequestMapping(value = "/getAllTeachers", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> getTeachers() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Teacher> list = new ArrayList<Teacher>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Teacher").list();
			t.commit();

		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return new ResponseEntity<List<Teacher>>(list, HttpStatus.OK);
	}

	/**
	 * @return Display all teachers By teacherId
	 */

	@RequestMapping(value = "/teacher-search", method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> serachTeacher(@RequestParam("teacherId") String teacherId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Teacher> list = new ArrayList<Teacher>();

		String hql = "";
		if (teacherId != "") {
			hql = "from Teacher where teacherId='" + teacherId + "'";
		}

		try {
			t = session.beginTransaction();
			list = session.createQuery(hql).list();
			t.commit();

		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<List<Teacher>>(list, HttpStatus.OK);
	}

	/**
	 * update a teacher By teacherId
	 * 
	 * @param teacherId
	 * 
	 */

	@RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTeacher(@RequestParam("teacherId") String teacherId,
			@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("telNo") String telNo, @RequestParam("email") String email,
			@RequestParam("qualification") String qualification) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			String hql = "UPDATE Teacher set name=:name, address=:address,telNo=:telNo, email=:email,qualification=:qualification where teacherId=:teacherId ";
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("address", address);
			query.setParameter("telNo", telNo);
			query.setParameter("email", email);
			query.setParameter("qualification", qualification);
			query.setParameter("teacherId", teacherId);

			int result = query.executeUpdate();

			t.commit();

			logger.info(result+"Record Updated Successfully");
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Record Updated Successfully", HttpStatus.OK);
	}

	/**
	 * delete a teacher by teacher id
	 * 
	 * @param teacherId
	 */

	@RequestMapping(value = "/deleteTeacher", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTeacher(@RequestParam("teacherId") String teacherId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			String hql = "DELETE FROM Teacher where teacherId = :teacherId";
			Query query = session.createQuery(hql);
			query.setParameter("teacherId", teacherId);
			int result = query.executeUpdate();
			t.commit();

			logger.info(result+"Record Deleted Successfully");
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Record Deleted Successfully", HttpStatus.GONE);
	}
}
