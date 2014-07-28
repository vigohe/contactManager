package dao;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Company;

@Stateless
@Named
public class CompanyDao {

	@PersistenceContext(unitName="primary")
	EntityManager em;
		
	private List<Company> list;
	
	private List<Company> listDel = new ArrayList<Company>();
	
	public void create(Company company) {
		System.out.println("CompanyDao: Creando la compañia.");
		System.out.println(company);
		em.persist(company);
	}
	
	public void update(Company company) {
		System.out.println("CompanyDao: Actualizando la compañia");
		System.out.println(company);

		em.merge(company);

	}

	public void delete(Company company) {
		em.remove(em.merge(company));
	}

	public Company findById(int key) {
		return em.find(Company.class, key);
	}

	public List<Company> listAll() {
		
		if(list==null){
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
			Root<Company> companyRoot = criteria.from(Company.class);
			criteria.select(companyRoot);
			
			list = em.createQuery(criteria).getResultList();
		}
		
		return list;
	}
	
	public void actualizarLista(){
		
		for(Company company : listDel){
			this.delete(company);
		}	
		
		for(Company company : list){

			if(company.isNuevo()){
				this.create(company);
			}else if(company.isEditable()){
				this.update(company);
			}
			
			company.setNuevo(false);
			company.setEditable(false);
		}
		
		listDel.clear();
	}
	
	public void nuevaFila(){
		Company company = new Company();
		company.setNuevo(true);
		list.add(company);
	}
	
	public void eliminarFila(Company company){
		
		System.out.println("pre"+listDel);
		listDel.add(company);
		System.out.println("post"+listDel);
		System.out.println(listDel);
		if( list.remove(company) ){
			System.out.println("Se quito de la lista " + company);
		}
	}
	
	public void setEditable(Company company){
		company.setEditable(true);
	}

	public List<Company> getList() {
		return list;
	}

	public void setList(List<Company> list) {
		this.list = list;
	}

	public List<Company> getListDel() {
		return listDel;
	}

	public void setListDel(List<Company> listDel) {
		this.listDel = listDel;
	}


}
