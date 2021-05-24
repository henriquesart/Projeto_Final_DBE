package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.DAO;
import br.com.fiap.model.Cadastro;

@Named
@RequestScoped
public class CadastroBean {
	
private Cadastro user = new Cadastro();
	
	public String login() {
		boolean exist = new DAO<Cadastro>(Cadastro.class).exist(this.user);
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (exist) {
			context.getExternalContext().getSessionMap().put("user", this.user);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login executado", "Login"));
			System.out.println("login realizado");
			System.out.println(this.user);
			return "index?faces-redirect=true";			
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "erro"));
		return "login?faces-redirect=true";
					
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}

	public void save() {
		new DAO<Cadastro>(Cadastro.class).save(this.user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso"));
		System.out.println(this.user);
		
	}
	
	public List<Cadastro> getUsers(){
		return new DAO<Cadastro>(Cadastro.class).getAll();
	}

	public Cadastro getUser() {
		return user;
	}

	public void setUser(Cadastro user) {
		this.user = user;
	}
	
}
