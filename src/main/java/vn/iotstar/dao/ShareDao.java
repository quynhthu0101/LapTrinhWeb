package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.Share_22133060;
import vn.iotstar.entity.Video_22133060;

public class ShareDao implements IShareDao{

	@Override
	public Share_22133060 get(int shareId) {
		EntityManager enma = JpaConfig.getEntityManager();
		Share_22133060 Share_22133060 = enma.find(Share_22133060.class, shareId);
		return Share_22133060;
	}
	
}
