package br.com.empresa.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	public void remove(Filial filial) {
		filial = manager.merge(filial);
		this.manager.remove(filial);
	}
	
	@SuppressWarnings("unchecked")
	public List<Filial> getAll() {
		Query query = manager.createQuery("SELECT f FROM Filial f LEFT JOIN FETCH f.empresa e ");
		List<Filial> filiais = query.getResultList();
		return filiais;
	}
	
	public Filial find(Long id) {
		return this.manager.find(Filial.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Filial> getAllFiliais(Long id) {
		
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT f FROM Filial f WHERE f.empresa.id = :id");
		
		Query query = manager.createQuery(jpql.toString());
		query.setParameter("id", id);
		
		List<Filial> filiais = query.getResultList();
		
		return filiais;
	}
}
