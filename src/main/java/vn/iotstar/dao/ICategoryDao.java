package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Category_22133060;

public interface ICategoryDao {
	int count();

	List<Category_22133060> findAll(int offset, int pageSize);

	List<Category_22133060> searchByName(String catname);

	List<Category_22133060> findAll();

	Category_22133060 findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category_22133060 Category_22133060);
	
	void insert(Category_22133060 Category_22133060);
	
	Category_22133060 findFirstCategory();


}