package org.seusl.fas.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.seusl.fas.model.StudentLogin;

@WebServlet("/studentLogin")
public class StudentLoginSubmit extends HttpServlet{

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username, password;
		username = req.getParameter("username");
		password= req.getParameter("password");
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction t = null;
		List<StudentLogin> list = new ArrayList<StudentLogin>();
		
		try{
			t = session.beginTransaction();
			
			list = session.createQuery("from StudentLogin where student_stuid='"+ username +"' and password = '"+password+ "'").list();
			
			t.commit();
			Iterator itr = list.iterator();
			
			if(itr.hasNext()) {				
				HttpSession se = req.getSession();
				se.setAttribute("user-id", username);
				resp.sendRedirect("student-system/user.jsp");
			}
			else {
				resp.sendRedirect("index.html");
			}
			
		}catch(HibernateException e){
			if(t!=null){
				t.rollback();
			}
			e.printStackTrace();
		} finally{
			session.close();
		}
	}

	
}
