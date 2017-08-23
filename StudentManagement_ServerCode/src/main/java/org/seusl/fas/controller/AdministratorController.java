package org.seusl.fas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/admin")
public class AdministratorController {

	/*private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@RequestMapping(value="/login" , method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object loginCheck(@RequestParam("username")String username , @RequestParam("pwd")String pwd) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Administrator> list = new ArrayList<Administrator>();
//		Administrator admin = new Administrator();
		JSONObject obj = new JSONObject();
		try {
			t = session.beginTransaction();
			
			list = session.createQuery("from Administrator where username='"+ username +"' and password = '"+pwd+ "'" ).list();
			
			t.commit();
			
			Iterator<Administrator> itr = list.iterator();
			
			if(itr.hasNext()) {
//				admin = itr.next();	
				
				JsonParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(stringToParse);
				obj = new JSONObject("{ \"code\":\"session ok\"}");
				//return obj;						
			}
			else {
				obj = new JSONObject("{ \"Error\":\"username and password did not match\"}");
				//return obj;	
			}
		} catch(HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return obj;
	}*/
}
