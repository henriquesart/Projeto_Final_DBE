package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Cadastro;
import br.com.fiap.util.JPAUtil;

public class CadastroDAO {
	
	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(Cadastro setup) {
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Cadastro> getAll() {
		String jpql = "SELECT s FROM Setup s";
		TypedQuery<Cadastro> query = manager.createQuery(jpql, Cadastro.class);
		List<Cadastro> resultList = query.getResultList();
		return resultList;
	}




}
