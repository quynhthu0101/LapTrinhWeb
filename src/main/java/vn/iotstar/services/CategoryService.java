package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category_22133060;

public class CategoryService implements ICategoryService{
	ICategoryDao cateDao = new CategoryDao();

	@Override
	public void insert(Category_22133060 category) {
		cateDao.insert(category);	
	}

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category_22133060> findAll(int offset, int pageSize) {
		return cateDao.findAll(offset, pageSize);
	}

	@Override
	public List<Category_22133060> searchByName(String catname) {
		return cateDao.searchByName(catname);
	}

	@Override
	public List<Category_22133060> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category_22133060 findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
	}

	@Override
	public void update(Category_22133060 category) {
		cateDao.update(category);		
	}

	@Override
	public Category_22133060 findFirstCategory() {
	    return cateDao.findFirstCategory();
	}

	
	
}

