package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Video_22133060;

public class VideoService implements IVideoService {
	IVideoDao videoDao = new VideoDao();

	@Override
	public void insert(Video_22133060 video) {
		videoDao.insert(video);

	}

	@Override
	public void update(Video_22133060 video) {
		try {
			videoDao.update(video);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while updating video");
		}

	}

	@Override
	public void delete(String videoId) throws Exception {
		try {
			videoDao.delete(videoId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting video");
		}

	}

	@Override
	public Video_22133060 findById(String videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public List<Video_22133060> findAll() {
		return videoDao.findAll();
	}

	@Override
	public List<Video_22133060> findByVideoTitle(String title) {
		return videoDao.findByVideoTitle(title);
	}

	@Override
	public List<Video_22133060> findAll(int page, int pageSize) {
		return videoDao.findAll(page, pageSize);
	}

	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video_22133060> findVideosByCategoryId(int categoryId) {
		return videoDao.findVideosByCategoryId(categoryId);
	}

	@Override
	public int countLikesForVideo(int videoId) {
		return videoDao.countLikesForVideo(videoId);
	}
	
	

}
