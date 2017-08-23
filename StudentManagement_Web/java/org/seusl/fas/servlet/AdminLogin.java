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
import org.seusl.fas.model.Administrator;

@WebServlet("/admin-system/adminLogin")
public class AdminLogin extends HttpServlet {
	 
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username, password;
		username = req.getParameter("username");
		password= req.getParameter("pwd");
		
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction t = null;
		List<Administrator> list = new ArrayList<Administrator>();
		try{
			t = session.beginTransaction();
			
			list = session.createQuery("from Administrator where username='"+ username +"' and password = '"+password+ "'").list();
			t.commit();
			
			Iterator<Administrator> itr = list.iterator();
			
			if(itr.hasNext()) {
				Administrator admin = itr.next();
				HttpSession se = req.getSession();
				se.setAttribute("name", admin.getName());
				resp.sendRedirect("dashboard.jsp");
//				System.out.println("success ");
			}
			else {
				resp.sendRedirect("admin-login.html");
//				System.out.println("unsuccessful");
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
