package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "Shares")

public class Share_22133060 {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shareId;

    private String emails;

    private Date sharedDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User_22133060 user;

    @ManyToOne
    @JoinColumn(name = "videoId")
    private Video_22133060 video;

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	public User_22133060 getUser() {
		return user;
	}

	public void setUser(User_22133060 user) {
		this.user = user;
	}

	public Video_22133060 getVideo() {
		return video;
	}

	public void setVideo(Video_22133060 video) {
		this.video = video;
	}
    
	public String toString() {
	    return "shareId=" + shareId +
	            ", emails='" + emails + '\'' +
	            ", sharedDate=" + sharedDate +
	            ", user=" + (user != null ? user.getUsername() : "null") +
	            ", video=" + (video != null ? video.getVideoId() : "null") + "\n";
	}


}
