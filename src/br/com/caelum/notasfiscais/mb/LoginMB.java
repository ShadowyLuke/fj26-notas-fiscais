package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB {
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin() {
		UsuarioDao dao = new UsuarioDao();
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) 
			return "produto?faces-redirect=true";
		else {
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}
