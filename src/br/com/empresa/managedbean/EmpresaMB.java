package br.com.empresa.managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.empresa.entidade.Empresa;
import br.com.empresa.sessionbeans.EmpresaRepository;

@ManagedBean
public class EmpresaMB {

	@EJB
	private EmpresaRepository repositorio;
	
	private Empresa empresa = new Empresa();
	
	private List<Empresa> empresasCache;
	
	public void adiciona() {

		if (this.empresa.getId() != null) {
			if (validarCampos()) {

				this.repositorio.edit(this.empresa);
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empresa alterada com sucesso!", null));
			}
		} else {
			if (validarCampos()) {
				this.repositorio.save(this.empresa);
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empresa cadastrada com sucesso!", null));
			}
		}

		this.empresa = new Empresa();
		this.empresasCache = null;
		
	}
	
	public boolean validarCampos() {
		
		if (this.empresa.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo nome é obrigatório!", null)); 
			return false;
		} else if (this.empresa.getCnpj().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo cnpj é obrigatório!", null));
			return false;
		}
		
		return true;
	}
	
	public List<Empresa> getEmpresas() {
		
		if (this.empresasCache == null) {
			this.empresasCache = this.repositorio.getAll();
		}
		
		return this.empresasCache;
	}
	
	public Empresa editar(Long id) {
		this.empresa = this.repositorio.find(id);
		return empresa;
	}
	
	public void remove(Long id) {
		this.repositorio.remove(empresa);
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Empresa removida com sucesso!", null));
		this.empresasCache = repositorio.getAll();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
