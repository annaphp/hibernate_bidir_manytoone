package repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Cookie;

public class CookieRepository {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public Long save(Cookie cookie){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		long id = (Long) session.save(cookie);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public void update (Cookie cookie){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(cookie);
		session.getTransaction().commit();
		session.close();
	}
	
	public Cookie findCookieById(Long id){
		Session session = sessionFactory.openSession();
		Cookie cookie = session.get(Cookie.class, id);
		System.out.println(cookie.getBox());
		session.close();
		return cookie;
	}
	
	public void delete(Cookie cookie){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(cookie);
		session.getTransaction().commit();
		session.close();
	}

}
