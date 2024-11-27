package vn.iotstar.dao;
import java.util.List;

import vn.iotstar.entity.Video_22133060;

public interface IVideoDao {
	int count();

    List<Video_22133060> findAll(int page, int pageSize);

    List<Video_22133060> findByVideoTitle(String title);

    List<Video_22133060> findAll();

    Video_22133060 findById(String videoId);

    void delete(String videoId) throws Exception;

    void update(Video_22133060 video);

    void insert(Video_22133060 video);
    
    List<Video_22133060> findVideosByCategoryId(int categoryId);
    
    int countLikesForVideo(int videoId);
}
