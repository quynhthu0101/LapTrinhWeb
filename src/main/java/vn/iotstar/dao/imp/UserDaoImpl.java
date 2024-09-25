package vn.iotstar.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.imp.UserServiceImpl;

public class UserDaoImpl extends DBConnection implements UserDao{

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public User get(int id) {
		String sql = "SELECT * FROM user WHERE id =?";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changePassword(String username, String newPassword) {
		String sql = "UPDATE user SET password = ? WHERE username = ?";

	    try {
	        conn = getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, newPassword);
	        ps.setString(2, username);
	        
	        int result = ps.executeUpdate();
	        if (result > 0) {
	            return true; // Password updated successfully
	        } else {
	            return false; // No rows updated, username not found
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // An error occurred during the update
	    } 
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from user where phone = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	
	public User findByUserName(String username) {
		String sql = "SELECT * FROM user WHERE username = ? ";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO user (email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
				try {
				conn = super.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getFullName());
				ps.setString(4, user.getPassWord());
				ps.setString(5, user.getAvatar());
				ps.setInt(6,user.getRoleid());
				ps.setString(7,user.getPhone());
				ps.setDate(8, user.getCreatedDate());
				ps.executeUpdate();
				} catch (Exception e) {e.printStackTrace();}
		
	}

	@Override
	public List<User> findAll(){
		String sql = "SELECT * FROM user";
		List<User> list = new ArrayList<User>();
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(
						new User(
								rs.getInt("id"),
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("fullname"),
								rs.getString("images"),
								rs.getInt("roleid"),
								rs.getString("phone"),
								rs.getDate("createdDate")
								)							
						);
			}
			return list;
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from user where email = ?";
		try {
		conn = super.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from user where username = ?";
		try {
		conn = super.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
}
