package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.UserDao;
import vn.iotstar.entity.User_22133060;

public class UserService implements IUserService {
	IUserDao UserDao = new UserDao();

	@Override
	public User_22133060 get(String id) {
		return UserDao.get(id);
	}

	@Override
	public void insert(User_22133060 User_22133060) {
		UserDao.insert(User_22133060);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return UserDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String User_22133060name) {
		return UserDao.checkExistUsername(User_22133060name);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return UserDao.checkExistPhone(phone);
	}

	@Override
	public User_22133060 findByUserName(String User_22133060name) {
		return UserDao.findByUserName(User_22133060name);
	}

	@Override
	public List<User_22133060> findAll() {
		return UserDao.findAll();
	}

	@Override
	public boolean changePassword(String User_22133060name, String newPassword) {
		return UserDao.changePassword(User_22133060name, newPassword);
	}

	@Override
	public void delete(int id) throws Exception {
		UserDao.delete(id);
	}

	@Override
	public void update(User_22133060 User_22133060) {
		UserDao.update(User_22133060);
	}

	@Override
	public User_22133060 login(String Username, String password) {
		return UserDao.login(Username, password);
	}
	
	public static void main(String[] args) {
		IUserDao UserDao = new UserDao();
		System.out.print(UserDao.findByUserName("quynhthu"));
	}
	
	
}
