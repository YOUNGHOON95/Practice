package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.LoginOkAction;
import kr.or.bit.service.myshop.MyShopInfoAction;

/**
 * Servlet implementation class MyShopController
 */
@WebServlet("/myshop/*")
public class MyShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MyShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	System.out.println("THIS IS MYSHOP");
    	System.out.println("url_Command : "+url_Command);
    	Action action=null;
    	ActionForward forward=null;
    	HttpSession session =null;;
    	
    		//myshop 메인
		 if(url_Command.equals("/myshop")) {
//			 session = request.getSession();
//			 boolean logincheck=(boolean) session.getAttribute("isLogined");
//			 if(!logincheck) {
//		    		System.out.println("SESSOION IS NULL");
//		    		 response.sendRedirect("/WEB-INF/views/member/Login.jsp");
//		 }
	    		System.out.println("myshop.jsp VIEW");
	    		action = new MyShopInfoAction();
	    		forward = action.execute(request, response);
		 }
		 else if(url_Command.equals("/myshop")) {
			 
		 }
    	//////////////////////////////////////////////////////
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			System.out.println("true");
    			System.out.println(forward.getPath());
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			System.out.println("false");
   			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
