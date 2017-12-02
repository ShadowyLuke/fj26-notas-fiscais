package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@RequestScoped
public class ProdutoMB {
	
	@Inject
	private ProdutoDao dao;
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	@Transactional
	public void save() {		
		if(this.produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		newProduto();
		this.produtos = dao.listaTodos();
	}
	
	public void load() {
		this.produtos = dao.listaTodos();
	}

	public void remove(Produto produto) {
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
	public void newProduto() {
		this.produto = new Produto();
	}
	
	public int getTotalCost(){
		int total=0;
		for (Produto p : produtos) {
			total += p.getPreco();
		}
		return total;
	}
	
	public String getHeader() {
		if(this.produto.getId() == null) {
			return "Novo Produto";
		} else {
			return "Editar Produto";
		}
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			this.produtos = dao.listaTodos();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
