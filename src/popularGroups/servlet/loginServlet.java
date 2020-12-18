package popularGroups.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import popularGroups.dao.DBConnectionUtil;
import popularGroups.dao.LoginDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginDao loginDao = new LoginDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		
		try {
	
			if(loginDao.check(email, pwd)) {
				HttpSession session = request.getSession();
				session.setAttribute("email",email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Event.jsp");
				request.setAttribute("email", email);
				dispatcher.forward(request, response);
				
			}else {
				String msg = "Invalid email or password";
				request.setAttribute("msg", msg);
				response.sendRedirect("/WEB-INF/views/login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	
	}

}
