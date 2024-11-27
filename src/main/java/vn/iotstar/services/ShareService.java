package vn.iotstar.services;

import vn.iotstar.dao.IShareDao;
import vn.iotstar.dao.ShareDao;
import vn.iotstar.entity.Share_22133060;

public class ShareService implements IShareService{
	IShareDao shareDao = new ShareDao();

	@Override
	public Share_22133060 get(int shareId) {
		return shareDao.get(shareId);
	}
	
		
}
