package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category_22133060;
import vn.iotstar.entity.User_22133060;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.ultis.Constant;

@MultipartConfig()

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert",

		"/admin/category/edit", "/admin/category/update", "/admin/category/delete" })

public class CategoryController_22133060 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			
			String url = req.getRequestURI();
			if (url.contains("/admin/categories")) {
				List<Category_22133060> list = cateService.findAll();
				req.setAttribute("listcate", list);
				
				int pageSize = 6;  
			    int currentPage = 1;

			    // Lấy tham số trang từ URL nếu có
			    String pageParam = req.getParameter("page");
			    if (pageParam != null) {
			        try {
			        	currentPage = Integer.parseInt(pageParam);
			        } catch (NumberFormatException e) {
			        	currentPage = 1;
			        }
			    }

			    int offset = (currentPage - 1) * pageSize;

			    // Lấy danh sách category từ cơ sở dữ liệu
			    List<Category_22133060> listcate = cateService.findAll(offset, pageSize);
			    int totalCates = cateService.count();  
			    int totalPages = (int) Math.ceil((double) totalCates / pageSize);  

			    req.setAttribute("listcate", listcate);
			    req.setAttribute("totalPages", totalPages);
			    req.setAttribute("currentPage", currentPage);
				
				req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
			} else if (url.contains("/admin/category/add")) {
				req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
			} else if (url.contains("/admin/category/edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Category_22133060 category = cateService.findById(id);
				req.setAttribute("cate", category);
				req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
			} else if (url.contains("delete")){
				int id = Integer.parseInt(req.getParameter("id"));
				try {
					cateService.delete(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// chuyển trang
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
			}
			
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {			
			String url = req.getRequestURI();
			if (url.contains("/admin/category/insert")) {
				// lấy dữ liệu từ form
				String categoryname = req.getParameter("categoryname");
				String categorycode = req.getParameter("categorycode");
				int status = Integer.parseInt(req.getParameter("status"));
				String images = req.getParameter("images");
				// đưa dữ liệu vào model
				Category_22133060 category = new Category_22133060();
				category.setCategoryName(categoryname);
				category.setCategoryCode(categorycode);
				category.setStatus(status);
				String fname = "";
				String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();
				try {
					Part part = req.getPart("images");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;
						part.write(uploadPath + "/" + fname);
						category.setImages(fname);
					} else if (images != null) {
						category.setImages(images);
					} else {
						category.setImages("meo.png");
					}
				} catch (FileNotFoundException fne) {
					fne.printStackTrace();
				}
				// đưa model vào phương thức insert
				cateService.insert(category);
				// chuyển trang
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
			}
			if (url.contains("/admin/category/update")) {
				// lấy dữ liệu từ form
				int categoryid = Integer.parseInt(req.getParameter("categoryid"));
				String categoryname = req.getParameter("categoryname");
				String categorycode= req.getParameter("categorycode");
				int status = Integer.parseInt(req.getParameter("status"));
				String images = req.getParameter("images");
				// đưa dữ liệu vào model
				Category_22133060 category = cateService.findById(categoryid);
				String fileold = category.getImages();
				category.setCategoryName(categoryname);
				category.setCategoryCode(categorycode);
				category.setStatus(status);
				String fname = "";
				
				//xu ly image
				String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();
				try {
					Part part = req.getPart("images");
					if (part.getSize() > 0) {
						// xóa file cũ trên thư mục
						
						try {
							if (!category.getImages().substring(0, 5).equals("https"))
								deleteFile(uploadPath + "\\" + fileold);
						}
						catch (Exception ex){
							
						}
						
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;
						part.write(uploadPath + "/" + fname);
						category.setImages(fname);
					} else if (images != null) {
						category.setImages(images);
					} else {
						category.setImages(fileold);
					}
				} catch (FileNotFoundException fne) {
					fne.printStackTrace();
				}
				cateService.update(category);
				// chuyển trang
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
			}
			
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		
	}

	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);

	}

}