package br.com.caelum.notasfiscais.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.notasfiscais.modelo.Produto;

public class ProdutoDao {

	@Inject
	EntityManager manager;
	
	public void adiciona(Produto produto) {
		manager.persist(produto);
	}


	public void remove(Produto produto) {
		manager.remove(manager.merge(produto));
	}

	public void atualiza(Produto produto) {
		manager.merge(produto);
	}

	public List<Produto> buscaPorNome(String nome) {
		String jpql = "select p from Produto p where "
				+ " lower(p.nome) like :nome order by p.nome";
		return manager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome + "%").getResultList();
	}

	public List<Produto> listaTodos() {	
		CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));
		return manager.createQuery(query).getResultList();
	}
	
	public Produto buscaPorId(Long id) {
		return manager.find(Produto.class, id);
	}
}