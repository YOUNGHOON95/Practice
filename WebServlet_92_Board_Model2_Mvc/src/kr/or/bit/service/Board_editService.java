package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Board_editService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		  
			try {
				String idx = request.getParameter("idx");
				  
				  if(idx == null || idx.trim().equals("")){
					  out.print("<script>");
					  	out.print("alert('글번호 입력 오류')");
					  	out.print("location.href='board_list.jsp'");
					  out.print("</script>");
				  }
				  
				  BoardService service = BoardService.getInBoardService();
				  int result = 0;
				result = service.board_Edit(request);
				
				String msg="";
				String url="";
				if(result > 0){
					msg="edit success";
					url="board_list.jsp";
					System.out.println("여기까지 왔니");
				}else{
					msg="edit fail";
					url="board_edit.jsp?idx="+idx;
				}
				
			
				forward.setRedirect(false);
				  forward.setPath(url);
				  
				 if(msg != null && url != null){

					  out.print("<script>");
					  out.print("alert('"+msg+"');");	
					  out.print("location.href='"+url+"';");
					  out.print("</script>");				  	
					  
					  
				 }
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
			
	return forward;
	}

}
