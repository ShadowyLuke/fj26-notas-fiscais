package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
public class ProdutoMB {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	public void save() {
		ProdutoDao dao = new ProdutoDao();
		
		if(this.produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		newProduto();
		this.produtos = dao.listaTodos();
	}
	
	public void load() {
		ProdutoDao dao = new ProdutoDao();
		this.produtos = dao.listaTodos();
	}

	public void remove(Produto produto) {
		ProdutoDao dao = new ProdutoDao();
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
			this.produtos = new ProdutoDao().listaTodos();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
