package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.entity.User_22133060;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController_22133060 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			User_22133060 u = (User_22133060) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getAdmin() == 1) {
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			} else{
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}
}