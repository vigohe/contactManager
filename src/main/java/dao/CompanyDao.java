package dao;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.Company;

@RequestScoped
@Named
public class CompanyDao {

	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Resource
	private UserTransaction utx;
	
	@Inject
	private Company entity;

	
	public void create() {
		System.out.println("CompanyDao: Creando la compañia.");
		System.out.println(entity);
		
		try {
			utx.begin();
			em.persist(entity);
			utx.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Company company) {
		System.out.println("CompanyDao: Actualizando la compañia");
		System.out.println(company);
		
		try {
			utx.begin();
			em.merge(company);
			utx.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		em.remove(entity);
	}

	public Company findById(int key) {
		return em.find(Company.class, key);
	}

	public List<Company> listAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> companyRoot = criteria.from(Company.class);
		criteria.select(companyRoot);
		
		List<Company> valueArray = em.createQuery(criteria).getResultList();
		
		return valueArray;
	}

	public Company getEntity() {
		return entity;
	}

	
}
