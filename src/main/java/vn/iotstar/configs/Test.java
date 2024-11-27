package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Category_22133060;
import vn.iotstar.entity.User_22133060;
import vn.iotstar.entity.Video_22133060;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		User_22133060 cate = new User_22133060();
		
		cate.setUsername("anhthu");
		cate.setPassword("123");
		try {
			trans.begin();
			enma.persist(cate);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}