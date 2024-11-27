package vn.iotstar.services;
import java.util.List;

import vn.iotstar.entity.Video_22133060;

public interface IVideoService {
	void insert(Video_22133060 video);

    void update(Video_22133060 video);

    void delete(String videoId) throws Exception;

    Video_22133060 findById(String videoId);

    List<Video_22133060> findAll();

    List<Video_22133060> findByVideoTitle(String title);

    List<Video_22133060> findAll(int page, int pageSize);

    int count();
    
    List<Video_22133060> findVideosByCategoryId(int categoryId);
    
    int countLikesForVideo(int videoId);
}
