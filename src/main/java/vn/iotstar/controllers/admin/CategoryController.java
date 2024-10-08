package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.imp.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.imp.CategoryServiceImpl;
import vn.iotstar.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
	maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5	)
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("categories")) {
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//doi ten file 
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					//upload file
					part.write(uploadPath + "/" + fname);
					//ghi ten file vao data
					category.setImages(fname);
				} else {
					category.setImages("avata.png");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")){		
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			
			//luu hinh cu
			CategoryModel cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			
			//xu ly images
			
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//doi ten file 
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					//upload file
					part.write(uploadPath + "/" + fname);
					//ghi ten file vao data
					category.setImages(fname);
				} else {
					category.setImages("avata.png");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		
			
		} 
		
		
		
	}
	
	
}
