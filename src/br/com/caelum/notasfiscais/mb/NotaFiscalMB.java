package br.com.caelum.notasfiscais.mb;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@ViewScoped
public class NotaFiscalMB {
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private NotaFiscalDao nfdao;
	@Inject
	private ProdutoDao pdao;
	
	@Transactional
	public void gravar() {
		nfdao.adiciona(notaFiscal);
		
		this.notaFiscal = new NotaFiscal();
	}
	
	@Transactional
	public void guardaItem() {
		Produto produto = pdao.buscaPorId(idProduto);
		this.item.setProduto(produto);
		this.item.setValorUnitario(produto.getPreco());
		
		this.notaFiscal.getItens().add(item);
		this.item.setNotaFiscal(notaFiscal);
		
		this.item = new Item();
		this.idProduto = null;
	}
	
	public NotaFiscal getNotaFiscal() {
		return this.notaFiscal;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public Long getIdProduto() {
		return this.idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
}
