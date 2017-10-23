package com.hiyoo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiyoo.domain.User;
import com.hiyoo.service.ISystemMenuService;
import com.hiyoo.service.impl.SystemMenuServiceImpl;

/**
 * Servlet implementation class SystemMenuAction
 */
@WebServlet("/SystemMenuAction")
public class SystemMenuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemMenuAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User loginUser=(User)request.getSession().getAttribute("LoginUser");
		if(loginUser==null) {
			request.getRequestDispatcher("/errorAction?id=1001").forward(request, response);
		}else {
			ISystemMenuService menuService=new SystemMenuServiceImpl();
			response.getWriter().write(menuService.getAllSystemMenu(loginUser));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
