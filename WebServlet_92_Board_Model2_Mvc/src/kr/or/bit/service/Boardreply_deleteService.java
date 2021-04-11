package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class Boardreply_deleteService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		String idx_fk=request.getParameter("idx");//댓글의 원본 게시글 번호
		String no = request.getParameter("no");//댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");//댓글의 암호
		System.out.println(idx_fk + "/" + no + "/" + pwd + "/");
		if(idx_fk == null || no == null || pwd == null || no.trim().equals(""))
		{
			 out.print("<script>");
			 	out.print("history.back()");
			 out.print("</script>");

			return forward ; //더 이상 코드 실행하지 않아요
		}
		
		//parameter 정상인 경우
		BoardService service = BoardService.getInBoardService();
	
		
		try {
			int result =service.replyDelete(no, pwd);
			//처리하는 코드
		 	String msg="";
		    String url="";
		    System.out.println("result"+result);
		    
		    
		    if(result > 0){
		    	msg ="댓글 삭제 성공";
		    	url ="board_content.jsp?idx="+idx_fk;
		    	//System.out.println("if문 나오나");
		    }else{
		    	msg="댓글 삭제 실패";
		    	url="board_content.jsp?idx="+idx_fk;
		    	//System.out.println("else문");
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);

		    if(msg != null && url != null){

				  out.print("<script>");
				  out.print("alert('"+msg+"');");	
				  out.print("location.href='"+url+"';");
				  out.print("</script>");				  	
				  
			 }
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return forward;
	}
}
