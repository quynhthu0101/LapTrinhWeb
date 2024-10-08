package vn.iotstar.services.imp;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.imp.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.UserService;

public class CategoryServiceImpl implements ICategoryService{
	public ICategoryDao cateDao = new CategoryDaoImpl();
	
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		// TODO Auto-generated method stub
		return cateDao.findById(id);
	}

	@Override
	public void insert(CategoryModel category) {
		// TODO Auto-generated method stub
		cateDao.insert(category);
	}

	@Override
	public void update(CategoryModel category) {
		// TODO Auto-generated method stub
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(category.getCategoryid());
		if (cate != null) {
			cateDao.update(category);
		} 		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(id);
		if (cate != null) {
			cateDao.delete(id);
		} 	
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		// TODO Auto-generated method stub
		return cateDao.findName(keyword);
	}
	
	public static void main(String[] args) {
		ICategoryDao user = new CategoryDaoImpl();
		user.delete(3);
	}
	
}
