package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Category_22133060;
import vn.iotstar.entity.Share_22133060;
import vn.iotstar.entity.User_22133060;
import vn.iotstar.entity.Video_22133060;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IShareService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.ShareService;
import vn.iotstar.services.VideoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5	)
@WebServlet(urlPatterns = {"/videos", "/admin/videos", "/video/detail", "/admin/video/detail"})
public class VideoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public IVideoService videoservice = new VideoService();
	public ICategoryService cateService = new CategoryService();
	public IShareService shareService = new ShareService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			
			
			String url = req.getRequestURI();
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			if (url.contains("videos")) {
				List<Category_22133060> list = cateService.findAll();
				req.setAttribute("listcate", list);
				
				int cateId = cateService.findFirstCategory().getCategoryId();
				String categoryid = req.getParameter("id");
			    if (categoryid != null) {
			        try {
			        	cateId = Integer.parseInt(categoryid);
			        } catch (NumberFormatException e) {
			        	System.out.print(e.getMessage());
			        }
			    }
			    
			    List<Video_22133060> listVideo = videoservice.findVideosByCategoryId(cateId);
				req.setAttribute("listvideo", listVideo);		    
			    
				//admin
				User_22133060 u = (User_22133060) session.getAttribute("account");
				req.setAttribute("isAdmin", u.getAdmin());
				
				req.getRequestDispatcher("/views/video-list.jsp").forward(req, resp);
			}
			else if (url.contains("detail")) {
				
				String id = req.getParameter("id");
				Video_22133060 video = videoservice.findById(id);
				Category_22133060 cate = video.getCategory();
				Share_22133060 share = shareService.get(video.getVideoId());
				int like = videoservice.countLikesForVideo(video.getVideoId());
				if (video != null) {
					req.setAttribute("video", video);
					if (cate != null) 
						req.setAttribute("cate", cate);
					if (share != null) 
						req.setAttribute("share", share.toString());
					if (share != null) 
						req.setAttribute("like", like);
					else
						req.setAttribute("like", 0);
					req.getRequestDispatcher("/views/video-detail.jsp").forward(req, resp);
				}
				
			}
			
			
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}	
		
	}

	
	
	
	
}
