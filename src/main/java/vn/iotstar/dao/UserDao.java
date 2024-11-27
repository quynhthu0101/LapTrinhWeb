package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.User_22133060;

public class UserDao implements IUserDao {

	@Override
    public User_22133060 get(String id) {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            return enma.find(User_22133060.class, id);
        } finally {
            enma.close();
        }
    }

    @Override
    public void insert(User_22133060 User_22133060) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(User_22133060);
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
    public boolean checkExistEmail(String email) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User_22133060 u WHERE u.email = :email";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("email", email);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistUsername(String User_22133060name) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User_22133060 u WHERE u.username = :username";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("User_22133060name", User_22133060name);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User_22133060 u WHERE u.phone = :phone";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("phone", phone);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    public User_22133060 findByUserName(String Username) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT u FROM User_22133060 u WHERE u.username = :username";
        try {
            TypedQuery<User_22133060> query = enma.createQuery(jpql, User_22133060.class);
            query.setParameter("username", Username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with username: " + Username);
            return null;
        } finally {
            enma.close();
        }
    }



    @Override
    public List<User_22133060> findAll() {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            TypedQuery<User_22133060> query = enma.createNamedQuery("User_22133060.findAll", User_22133060.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean changePassword(String User_22133060name, String newPassword) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String jpql = "UPDATE User_22133060 u SET u.password = :newPassword WHERE u.username = :username";
            Query query = enma.createQuery(jpql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("User_22133060name", User_22133060name);
            int updated = query.executeUpdate();
            trans.commit();
            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            return false;
        } finally {
            enma.close();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User_22133060 User_22133060 = em.find(User_22133060.class, id);
            if (User_22133060 != null) {
                em.remove(User_22133060);
            } else {
                throw new Exception("User not found");
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User_22133060 User_22133060) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(User_22133060);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

	@Override
	public User_22133060 login(String username, String password) {
		User_22133060 User_22133060 = findByUserName(username);
		if (User_22133060 != null && password.equals(User_22133060.getPassword())) {
			return User_22133060;
		}
		return null;
	}
    
    
}
