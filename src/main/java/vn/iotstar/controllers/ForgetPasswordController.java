package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.imp.UserServiceImpl;
import vn.iotstar.ultis.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgetpassword")
public class ForgetPasswordController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
	}
	@SuppressWarnings("static-access")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String alertMsg = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
			return;
		}
		
		UserService service = new UserServiceImpl();

		if (!service.checkExistUsername(username)) {
			alertMsg = "Tài khoản không tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
			return;
		}
		boolean tmp = service.changePassword(username, password);
		if (tmp) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			alertMsg = "Có lỗi xảy ra";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/ForgetPassword.jsp").forward(req, resp);
		}
	}

	
}
