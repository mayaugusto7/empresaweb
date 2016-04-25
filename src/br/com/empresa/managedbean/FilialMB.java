package br.com.empresa.managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.empresa.entidade.Filial;
import br.com.empresa.sessionbeans.FilialRepository;

@ManagedBean
public class FilialMB {

	@EJB
	private FilialRepository repositorio;

	private Filial filial = new Filial();

	private List<Filial> filiaisCache;

	public void adiciona() {

		if (this.filial.getId() != null) {
			if (validarCampos()) {

				this.repositorio.edit(this.filial);
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Filial alterada com sucesso!", null));
			}
		} else {
			if (validarCampos()) {
				this.repositorio.save(this.filial);
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Filial cadastrada com sucesso!", null));
			}
		}

		this.filial = new Filial();
		this.filiaisCache = null;

	}

	public boolean validarCampos() {

		if (this.filial.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo nome é obrigatório!", null)); 
			return false;
		} else if (this.filial.getCnpj().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo cnpj é obrigatório!", null));
			return false;
		}

		return true;
	}

	public List<Filial> getEmpresas() {

		if (this.filiaisCache == null) {
			this.filiaisCache = this.repositorio.getAll();
		}

		return this.filiaisCache;
	}

	public Filial editar(Long id) {
		this.filial = this.repositorio.find(id);
		return filial;
	}

	public void remover() {
		this.repositorio.remove(filial);
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Filial removida com sucesso!", null));
		this.filial = new Filial();
		this.filiaisCache = repositorio.getAll();
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
}
