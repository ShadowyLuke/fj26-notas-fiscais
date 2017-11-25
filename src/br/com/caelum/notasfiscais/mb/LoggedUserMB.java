package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@SessionScoped
public class LoggedUserMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	public void logar(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void deslogar() {
		this.usuario = null;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public boolean isLoggedIn() {
		return this.usuario != null;
	}
}
