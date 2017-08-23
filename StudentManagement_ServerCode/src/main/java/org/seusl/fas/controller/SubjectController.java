package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gautam Kumar
 *
 */

@RestController
@RequestMapping(value = "/subjects")
public class SubjectController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	/**
	 * Insert the subject details
	 * 
	 * @param subjectId,
	 *            name, teacher_teacherId
	 * 
	 */

	@RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	public ResponseEntity<String> addSubject(@RequestParam("id") String id,
			@RequestParam("name") String name,@RequestParam("teacherId") String teacherId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			Subject subjectDetails=new Subject();
			subjectDetails.setId(id);
			subjectDetails.setName(name);
			subjectDetails.setTeacherId(teacherId);

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
		return new ResponseEntity<String>("Subject Details Added Successfully", HttpStatus.CREATED);
	}

	/**
	 * @return list of the subjects
	 */

	@RequestMapping(value = "/getAllSubjects", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> getAllSubjects() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Subject> list = new ArrayList<Subject>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Subject").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}

	/**
	 * search a subject BY id
	 * 
	 */

	@RequestMapping(value = "/serachSubject", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> serachSubject(@RequestParam("subjectId") String subjectId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Subject> list = new ArrayList<Subject>();

		String hql = "";
		if (subjectId != "") {
			hql = "from Subject where subjectId='" + subjectId + "'";
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
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/allSubjectId", method = RequestMethod.GET)
	public List<String> getSubjectId() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<String> list = new ArrayList<String>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("select id from Subject").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	/**
	 * 
	 * update the subject BY subjectId
	 * 
	 */

	@RequestMapping(value = "/updateSubject", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSubject(@RequestParam("id") String id,
			@RequestParam("name") String name,@RequestParam("teacherId") String teacherId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			Subject subjectInfo=new Subject();
			
			t = session.beginTransaction();
			String hql = "UPDATE Subject set name=:name, teacher_teacherId = :teacher_teacherId where id=:id ";
			Query query = session.createQuery(hql);
			query.setParameter("name",name);
			query.setParameter("teacher_teacherId",teacherId);
			query.setParameter("id",id);

			int result = query.executeUpdate();

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Subject Updated Successfully", HttpStatus.OK);
	}

	/**
	 * delete the subject BY id
	 * 
	 * @param subjectId
	 */

	@RequestMapping(value = "/deleteSubject", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSubject(@RequestParam("subjectId") String subjectId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();

			String hql = "DELETE FROM Subject where subjectId = :subjectId";
			Query query = session.createQuery(hql);
			query.setParameter("subjectId", subjectId);
			int result = query.executeUpdate();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<String>("Subject Deleted Successfully",HttpStatus.GONE);
	}

}
