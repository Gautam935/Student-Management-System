package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.seusl.fas.model.Student;
import org.seusl.fas.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/student")
public class StudentContoller {

	private final static Logger logger = Logger.getLogger(StudentContoller.class);
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	/**
	 * Save a Student Details
	 * 
	 * @param stuId,
	 *            name, address, telNo, email, acedemicYear
	 * 
	 */

	@RequestMapping(value = "/addStudents", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addStudent(@RequestParam("stuId") String stuId,
			@RequestParam("stuName") String stuName, @RequestParam("stuAddress") String stuAddress,
			@RequestParam("telNo") String telNo, @RequestParam("email") String email,
			@RequestParam("acedemicYear") String acedemicYear) {

		Session session = sessionFactory.openSession();
		Transaction t = null;

		Response response = new Response();
		Map<String, String> head = new HashMap<String, String>();
		Map<String, Object> body = new HashMap<String, Object>();
		Student studentInfo = new Student();

		try {
			t = session.beginTransaction();

			studentInfo.setId(stuId);
			studentInfo.setName(stuName);
			studentInfo.setAddress(stuAddress);
			studentInfo.setTelNo(telNo);
			studentInfo.setEmail(email);
			studentInfo.setAcedemicYear(acedemicYear);

			session.save(studentInfo);

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		head.put("Status", "Student Details Added Successfully");
		body.put("StudentInfoDetails", studentInfo);
		response.setHead(head);
		response.setBody(body);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * @return the List Of Students
	 */

	@RequestMapping(value = "/getStudents", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudents() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Student> list = new ArrayList<Student>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("from Student").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	/**
	 * Search Student By stuId
	 * 
	 * @return
	 */

	@RequestMapping(value = "/serachStudents", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> serachStudent(@RequestParam("stuId") String stuId) {

		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Student> list = new ArrayList<Student>();

		String hql = "";
		if (stuId != "") {
			hql = "from Student where stuId='" + stuId + "'";
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
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllStudentId", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudentId() {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Student> list = new ArrayList<Student>();

		try {
			t = session.beginTransaction();

			list = session.createQuery("select id from Student").list();
			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	/**
	 * Update the Student By stuId
	 * 
	 */

	@RequestMapping(value = "/updateStudents", method = RequestMethod.PUT)
	public ResponseEntity<HashMap<Integer, String>> updateStudent(@RequestParam("stuId") String stuId,
			@RequestParam("stuName") String stuName, @RequestParam("stuAddress") String stuAddress,
			@RequestParam("telNo") String telNo, @RequestParam("email") String email,
			@RequestParam("acedemicYear") String acedemicYear) {

		Session session = sessionFactory.openSession();
		Transaction t = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		try {
			t = session.beginTransaction();
			String hql = "UPDATE Student set name=:name, address=:address,telNo=:telNo, email=:email,acedemicYear=:acedemicYear where stuId=:stuId ";
			Query query = session.createQuery(hql);
			query.setParameter("name", stuName);
			query.setParameter("address", stuAddress);
			query.setParameter("telNo", telNo);
			query.setParameter("email", email);
			query.setParameter("acedemicYear", acedemicYear);
			query.setParameter("stuId", stuId);

			Integer result = query.executeUpdate();

			logger.info(result + " Record Updated Successfully");
			map.put(result, "Record Updated Successfully");

			t.commit();
		} catch (HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ResponseEntity<HashMap<Integer, String>>(map, HttpStatus.OK);

	}

	/**
	 * Delete Student By Id
	 * 
	 * @param stuId
	 */

	@RequestMapping(value = "/deleteStudents", method = RequestMethod.DELETE)
	public ResponseEntity<HashMap<Integer, String>> deleteStudent(@RequestParam("stuId") String stuId) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		try {
			t = session.beginTransaction();

			String hql = "DELETE FROM Student where stuId = :stuId";
			Query query = session.createQuery(hql);
			query.setParameter("stuId", stuId);
			Integer result = query.executeUpdate();

			logger.info(result + "Record Deleted Successfully");
			map.put(result, "Record Deleted Successfully");
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
