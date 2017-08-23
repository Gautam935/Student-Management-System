package org.seusl.fas.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.seusl.fas.dto.ResultRequest;
import org.seusl.fas.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/result")
public class ResultController {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	

	@RequestMapping(value="/addResult" , method=RequestMethod.POST)
	public ResponseEntity<?> addResult(@RequestBody ResultRequest resultRequestDTO) {
		
		Result result= new Result();
		result.setStuId(resultRequestDTO.getStuId());
		result.setSubjectName(resultRequestDTO.getSubjectName());
		result.setResult(resultRequestDTO.getResult());
		
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			session.save(result);
			
			t.commit();
			return new ResponseEntity<String>("Result Added Successfully",HttpStatus.OK);
		} catch(Exception e ) {
			if(t!= null) {
				t.rollback();
			}
			JSONObject json = new JSONObject();
			json.put("error", "something went wrong");    
			return new ResponseEntity<Object>(json,HttpStatus.BAD_REQUEST);
		} finally {
			session.close();
		}

	}
	
	@RequestMapping(value="/getResults",method=RequestMethod.GET)
	public List<Result> getResults(@RequestParam("id") String id) {
		List<Result> list = new ArrayList<Result>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Result where student_stuId='" + id+"'").list();
			
			t.commit();
		} catch(HibernateException e ) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	
	@RequestMapping(value="/getAllResults", method=RequestMethod.GET)
	public List<Result> getAllResults() {
		List<Result> list = new ArrayList<Result>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Result").list();
			
			t.commit();
		} catch(HibernateException e ) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	@RequestMapping(value="/deleteResult", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteResult(@RequestParam("id") String id) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			session.delete(session.find(Result.class, new Integer(id)));
			
			t.commit();
			
			return new ResponseEntity<String>("Result Deleted Successfully",HttpStatus.OK);
		} catch(Exception e ) {
			if(t != null) {
				t.rollback();
			}
			JSONObject object = new JSONObject();
			object.put("Error", e.getMessage());
			return new ResponseEntity<Object>(object,HttpStatus.BAD_REQUEST);		
		} finally {
			session.close();
		}
		
	}
}
