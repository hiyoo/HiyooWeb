package com.hiyoo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiyoo.domain.User;
import com.hiyoo.service.impl.UserServiceImpl;
import com.hiyoo.tools.KeyProcessor;
import com.hiyoo.tools.SysProcessor;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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
		
		User user=new User();
		user.setUid(request.getParameter("username"));
		user.setPwd(KeyProcessor.encryption((request.getParameter("password"))));
		try {
			user.setLastLoginIP(SysProcessor.getIpAddr(request));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		UserServiceImpl service=new UserServiceImpl(user);
		if(service.login()) {
			request.getSession().setAttribute("LoginUser", user);
			System.out.println("gid:"+user.getGid());
			request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
		}else {			
			request.getRequestDispatcher("/errorAction?id=1000").forward(request, response);
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
