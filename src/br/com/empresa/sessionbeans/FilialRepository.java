package br.com.empresa.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.empresa.entidade.Filial;

@Stateless
public class FilialRepository {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Filial filial) {
		this.manager.persist(filial);
	}
	
	public void edit(Filial filial) {
		this.manager.merge(filial);
	}
	
	public void removeById(Long id) {
		Filial filial = this.manager.find(Filial.class, id);
		this.manager.remove(filial);
	}
	
	public List<Filial> getAll() {
		TypedQuery<Filial> query = manager.createQuery("SELECT f FROM Filial ", Filial.class);
		return query.getResultList();
	}
	
	public Filial find(Long id) {
		return this.manager.find(Filial.class, id);
	}
}
