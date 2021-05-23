package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.CadastroDAO;
import br.com.fiap.model.Cadastro;

@Named
@RequestScoped
public class CadastroBean {
	
	private Cadastro user = new Cadastro();
	
	public void save() {
		new CadastroDAO<Cadastro>(Cadastro.class).save(this.user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso!"));
	}
	
	public List<Cadastro> getUsers(){
		return new CadastroDAO<Cadastro>(Cadastro.class).getAll();
	}
	
	public Cadastro getUser() {
		return user;
	}

	public void setUser(Cadastro user) {
		this.user = user;
	}
	
}
