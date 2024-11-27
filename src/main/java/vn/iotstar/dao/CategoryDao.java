package vn.iotstar.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.Category_22133060;

public class CategoryDao implements ICategoryDao {
	
	public void insert(Category_22133060 Category_22133060) {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(Category_22133060);// insert vào bảng
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}

	@Override

	public void update(Category_22133060 Category_22133060) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(Category_22133060);// update vào bảng
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override

	public void delete(int cateid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category_22133060 Category_22133060 = enma.find(Category_22133060.class, cateid);
			if (Category_22133060 != null) {
				enma.remove(Category_22133060);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override

	public Category_22133060 findById(int cateid) {
		
		EntityManager enma = JpaConfig.getEntityManager();
		Category_22133060 Category_22133060 = enma.find(Category_22133060.class, cateid);
		return Category_22133060;

	}


	@Override

	public List<Category_22133060> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category_22133060 c";
        return enma.createQuery(jpql, Category_22133060.class).getResultList();
    }

	@Override

	public List<Category_22133060> searchByName(String catname) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category_22133060 c WHERE c.catename like :catename";
		TypedQuery<Category_22133060> query = enma.createQuery(jpql, Category_22133060.class);
		query.setParameter("catename", "%" + catname + "%");
		return query.getResultList();

	}

//	@Override
//
//	public List<Category_22133060> findAll(int page, int pagesize) {
//		EntityManager enma = JpaConfig.getEntityManager();
//		TypedQuery<Category_22133060> query = enma.createNamedQuery("Category_22133060.findAll", Category_22133060.class);
//		query.setFirstResult(page * pagesize);
//		query.setMaxResults(pagesize);
//		return query.getResultList();
// 	}
	
	@Override
	public List<Category_22133060> findAll(int offset, int pageSize) {
	    List<Category_22133060> cates = new ArrayList<>();
	    
	    try {
	        EntityManager em = JpaConfig.getEntityManager();
	        
	        // JPQL query to fetch authors, applying pagination
	        String jpql = "SELECT c FROM Category_22133060 c";
	        TypedQuery<Category_22133060> query = em.createQuery(jpql, Category_22133060.class);
	        query.setFirstResult(offset);  
	        query.setMaxResults(pageSize);
	        
	        cates = query.getResultList();
	        
	        em.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return cates;
	}


	@Override

	public int count() {
		
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category_22133060 c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
		
	}
	
	@Override
	public Category_22133060 findFirstCategory() {
	    Category_22133060 category = null;

	    try {
	        EntityManager em = JpaConfig.getEntityManager();

	        String jpql = "SELECT c FROM Category_22133060 c ORDER BY c.categoryId ASC";
	        TypedQuery<Category_22133060> query = em.createQuery(jpql, Category_22133060.class);
	        query.setMaxResults(1); 

	        category = query.getSingleResult();

	        em.close();
	    } catch (NoResultException e) {
	        System.out.println("No category found in the database.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return category;
	}

	
}
