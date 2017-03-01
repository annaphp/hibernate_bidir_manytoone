package repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import anna.model.Box;

public class BoxRepository {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public Long save(Box box){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Long id = (Long) session.save(box);
		session.getTransaction().commit();
		session.close();
		return id;	
	}

	public void update (Box box){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(box);
		session.getTransaction().commit();
		session.close();
	}
	
	public Box findBoxById(Long id){
		Session session = sessionFactory.openSession();
		Box box = session.get(Box.class, id);
		session.close();
		return box;
	}
	
	public void delete(Box box){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(box);
		session.getTransaction().commit();
		session.close();
	}
}
