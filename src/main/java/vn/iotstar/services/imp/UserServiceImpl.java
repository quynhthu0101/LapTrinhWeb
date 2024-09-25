package vn.iotstar.services.imp;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.imp.UserDaoImpl;
import vn.iotstar.services.UserService;
import vn.iotstar.models.User;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl(); // lấy tất cả hàm trong tầng dao
	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}
	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		userDao.insert(new User(email, username, fullname,password,	null,5,phone,date));
		return true;

	}
	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public User login(String username, String password) {
		User user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
		return user;
		}
		return null;
	}
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
		}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}
	
	
	@Override
	public boolean changePassword(String username, String newPassword) {
		return userDao.changePassword(username, newPassword);
	}
	public static void main(String[] args) {
		UserService user = new UserServiceImpl();
		System.out.print(user.changePassword("quynhthu", "123"));
	}
}
