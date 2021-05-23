package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fiap.model.Cadastro;
import br.com.fiap.util.JPAUtil;

public class DAO<T> {
	
	private Class<T> classs;

	public DAO(Class<T> classs) {
		this.classs = classs;
	}

	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(T t) {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<T> getAll() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classs);
		query.select(query.from(classs));
		TypedQuery<T> createQuery = manager.createQuery(query);
		List<T> resultList = createQuery.getResultList();
		return resultList;
	}

	public T findById(Long id) {
		return manager.find(classs, id);
	}

	public void update(Cadastro cadastro) {
		manager.getTransaction().begin();
		manager.merge(cadastro);
		manager.flush();
		manager.getTransaction().commit();
	}

	public boolean exist(Cadastro cadastro) {
		TypedQuery<Cadastro> query = manager.createQuery("SELECT u from User u WHERE "
				+ "email= :email AND "
				+ "password = :password", Cadastro.class);
		
		query.setParameter("email", cadastro.getEmail());
		query.setParameter("password", cadastro.getSenha());
		
		Cadastro result = query.getSingleResult();
		return result != null;
	}
	
	public T delete(Cadastro cadastro) {
		TypedQuery<Cadastro> query = manager.createQuery("DELETE FROM User WHERE u", Cadastro.class);
		return null;
	}



}
