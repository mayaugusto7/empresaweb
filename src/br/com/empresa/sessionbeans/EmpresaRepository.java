package br.com.empresa.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.empresa.entidade.Empresa;

@Stateless
public class EmpresaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Empresa empresa) {
		this.manager.persist(empresa);
	}
	
	public void edit(Empresa empresa) {
		this.manager.merge(empresa);
	}
	
	public void remove(Empresa empresa) {
		this.manager.remove(empresa);
	}
	
	public List<Empresa> getAll() {
		TypedQuery<Empresa> query = manager.createQuery("SELECT e FROM Empresa e", Empresa.class);
		return query.getResultList();
	}
	
	public Empresa find(Long id) {
		return this.manager.find(Empresa.class, id);
	}
}
