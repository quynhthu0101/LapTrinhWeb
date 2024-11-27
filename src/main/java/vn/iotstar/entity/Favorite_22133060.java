package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Favorites")
public class Favorite_22133060 implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteId;

   
    private Date likedDate;

    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video_22133060 video;

    @ManyToOne
    @JoinColumn(name = "username")
    private User_22133060 user;

	public int getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Date getLikedDate() {
		return likedDate;
	}

	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}

	public Video_22133060 getVideo() {
		return video;
	}

	public void setVideo(Video_22133060 video) {
		this.video = video;
	}

	public User_22133060 getUser() {
		return user;
	}

	public void setUser(User_22133060 user) {
		this.user = user;
	}

    
}
