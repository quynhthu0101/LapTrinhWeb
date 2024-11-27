package vn.iotstar.dao;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.Category_22133060;
import vn.iotstar.entity.Video_22133060;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		EntityManager em = JpaConfig.getEntityManager();
		String jpql = "SELECT count(v) FROM Video v";
		Query query = em.createQuery(jpql);
		int count = ((Long) query.getSingleResult()).intValue();
		em.close();
		return count;
	}

	@Override
	public List<Video_22133060> findAll(int page, int pageSize) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c";
        return enma.createQuery(jpql, Video_22133060.class).getResultList();
		
	}

	@Override
	public List<Video_22133060> findByVideoTitle(String title) {
		EntityManager em = JpaConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
		TypedQuery<Video_22133060> query = em.createQuery(jpql, Video_22133060.class);
		query.setParameter("title", "%" + title + "%");
		List<Video_22133060> videos = query.getResultList();
		em.close();
		return videos;
	}

	@Override
	public List<Video_22133060> findAll() {
		EntityManager em = JpaConfig.getEntityManager();
		TypedQuery<Video_22133060> query = em.createNamedQuery("Video.findAll", Video_22133060.class);
		List<Video_22133060> videos = query.getResultList();
		em.close();
		return videos;
	}

	@Override
	public Video_22133060 findById(String videoId) {
		EntityManager em = JpaConfig.getEntityManager();
		Video_22133060 video = em.find(Video_22133060.class, videoId);
		em.close();
		return video;
	}

	@Override
	public void delete(String videoId) throws Exception {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Video_22133060 video = em.find(Video_22133060.class, videoId);
			if (video != null) {
				em.remove(video);
			} else {
				throw new Exception("Video not found");
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	@Override
	public void update(Video_22133060 video) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(video);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	@Override
	public void insert(Video_22133060 video) {
		EntityManager em = JpaConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(video);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	@Override
	public List<Video_22133060> findVideosByCategoryId(int categoryId) {
		List<Video_22133060> videos = new ArrayList<>();

	    try {
	        EntityManager em = JpaConfig.getEntityManager();
	        String jpql = "SELECT v FROM Video_22133060 v WHERE v.category.categoryId = :categoryId";
	        TypedQuery<Video_22133060> query = em.createQuery(jpql, Video_22133060.class);
	        query.setParameter("categoryId", categoryId);

	        videos = query.getResultList();

	        em.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return videos;
	}

	@Override
	public int countLikesForVideo(int videoId) {
		EntityManager em = JpaConfig.getEntityManager();
        String jpql = "SELECT count(f) FROM Favorite_22133060 f WHERE f.video.videoId = :videoId";
        Query query = em.createQuery(jpql);
        query.setParameter("videoId", videoId);
 
        int count = ((Long) query.getSingleResult()).intValue();
        em.close();
        return count;
	}
	
	

}
