package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Videos")
public class Video_22133060 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	@Column(name = "Title", columnDefinition = "nvarchar(200) NULL")
	private String title;
	
	@Column(name = "Poster", columnDefinition = "nvarchar(50) NULL")
	private String poster;
	@Column(name = "Views", columnDefinition = "int NULL")
	private int views;
	@Column(name = "Description", columnDefinition = "nvarchar(200) NULL")
	private String description;
	
	@Column(name = "Active", columnDefinition = "int NULL")
	private int active;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category_22133060 category;

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Favorite_22133060> favorites;

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Share_22133060> shares;

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Category_22133060 getCategory() {
		return category;
	}

	public void setCategory(Category_22133060 category) {
		this.category = category;
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

	
}