package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Board_deleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ActionForward forward = null;
		
		
		try {
			request.setCharacterEncoding("UTF-8");
			String idx = request.getParameter("idx");
			String pwd = request.getParameter("pwd");
			
			BoardService service = BoardService.getInBoardService();
			int result =service.board_Delete(idx, pwd);
			
			String msg="";
			String url="";
			if(result > 0){
				msg="delete success";
				url="board_list.jsp";
			}else{
				msg="delete fail";
				url="board_list.jsp";
			}
			
			if(msg != null && url != null){

				  out.print("<script>");
				  out.print("alert('"+msg+"');");	
				  out.print("location.href='"+url+"';");
				  out.print("</script>");				  	
			}
			
			
		} catch (Exception e) {
			
		}
		return forward;
	}

}
