package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Cadastro;
import br.com.fiap.util.JPAUtil;

public class CadastroDAO {

	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(Cadastro cadastro) {
		manager.getTransaction().begin();
		manager.persist(cadastro);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Cadastro> getAll() {
		String jpql = "SELECT s FROM Cadastro s";
		TypedQuery<Cadastro> Query = manager.createQuery(jpql, Cadastro.class);
		List<Cadastro> resultList = Query.getResultList();
		return resultList;
	}

}
