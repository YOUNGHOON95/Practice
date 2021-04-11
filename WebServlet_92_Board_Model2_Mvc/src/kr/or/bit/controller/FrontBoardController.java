package kr.or.bit.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.Board_deleteService;
import kr.or.bit.service.Board_editService;
import kr.or.bit.service.Boardreply_deleteService;


@WebServlet("*.do")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
        
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	String dir = "/board";
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length()+dir.length());
    	
    	System.out.println(requestURI);
    	System.out.println(contextPath);
    	System.out.println(url_Command);
    	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(url_Command.equals("/board_edit.do")) {
    		//UI만
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_edit.jsp");

    	}else if(url_Command.equals("/board_editok.do")) {
    		action = new Board_editService();
    		forward = action.execute(request, response);
    		System.out.println("dd"+forward);
    		
    	}else if(url_Command.equals("/board_delete.do")) {
    		//UI
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_delete.jsp");
    		
    	}else if(url_Command.equals("/board_deleteok.do")) {
    		//UI 로직
    		action = new Board_deleteService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/boardreply_deleteOk.do")) {
    		action = new Boardreply_deleteService();
    		forward = action.execute(request, response);
    	}
    	
    	System.out.println("aa"+forward);
    	
    	if(forward != null) {
    		
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	//Board_deleteService
    	//Board_editService
    	//Boardreply_deleteService
	}
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
