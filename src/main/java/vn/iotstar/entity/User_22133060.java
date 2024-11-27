package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "User_22133060.findAll", query = "SELECT u FROM User_22133060 u")
public class User_22133060 implements Serializable{
	@Id
    private String username;

    private String password;
    private String phone;
    private String fullname;
    private String email;
    private int admin;
    private int active;
    private String images;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite_22133060> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Share_22133060> shares;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<Favorite_22133060> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite_22133060> favorites) {
		this.favorites = favorites;
	}

	public List<Share_22133060> getShares() {
		return shares;
	}

	public void setShares(List<Share_22133060> shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
	    return "User_22133060{" +
	            "username='" + username + '\'' +
	            ", password='" + password + '\'' +
	            ", phone='" + phone + '\'' +
	            ", fullname='" + fullname + '\'' +
	            ", email='" + email + '\'' +
	            ", admin=" + admin +
	            ", active=" + active +
	            ", images='" + images + '\'' +
	            '}';
	}



    
}